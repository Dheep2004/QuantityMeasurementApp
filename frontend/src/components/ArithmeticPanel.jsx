import { useEffect, useState } from 'react';
import { UNITS } from '../constants/units.js';
import { ADD_URL, SUBTRACT_URL, DIVIDE_URL, CONVERT_URL } from '../constants/api.js';

const OPERATORS = ["ADD", "SUBTRACT", "DIVIDE"];

const OPERATOR_SYMBOLS = {
    ADD: "+",
    SUBTRACT: "\u2212",
    DIVIDE: "\u00F7"
};

export default function ArithmeticPanel({ jwt, measurementType }) {

    const [value1, setValue1] = useState("");

    const [value2, setValue2] = useState("");

    const [unit1, setUnit1] = useState(UNITS[measurementType][0]);

    const [unit2, setUnit2] = useState(UNITS[measurementType][0]);

    const [operatorIndex, setOperatorIndex] = useState(0);

    const [result, setResult] = useState(null);

    const [originalResultDTO, setOriginalResultDTO] = useState(null);

    const [resultUnit, setResultUnit] = useState(null);

    const [fieldError, setFieldError] = useState("");

    const isTemperature = measurementType === "TEMPERATURE";

    const operation = OPERATORS[operatorIndex];

    useEffect(() => {

        setUnit1(UNITS[measurementType][0]);

        setUnit2(UNITS[measurementType][0]);

        setResult(null);

        setOriginalResultDTO(null);

        setResultUnit(null);

        setFieldError("");

    }, [measurementType]);

    function cycleOperator() {

        setOperatorIndex((operatorIndex + 1) % OPERATORS.length);
    }

    async function handleCalculate() {

        if (value1 === "" || value2 === "") {

            setFieldError("Please enter both values.");

            return;
        }

        setFieldError("");

        const requestBody = {

            thisQuantity: {
                value: Number(value1),
                unit: unit1,
                measurementType: measurementType
            },

            thatQuantity: {
                value: Number(value2),
                unit: unit2,
                measurementType: measurementType
            }

        };

        const url =
            operation === "ADD"
                ? ADD_URL
                : operation === "SUBTRACT"
                    ? SUBTRACT_URL
                    : DIVIDE_URL;

        try {

            const response = await fetch(url, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + jwt
                },

                body: JSON.stringify(requestBody)

            });

            if (!response.ok) {

                const errorBody = await response.json().catch(() => null);

                setResult({
                    text: errorBody && errorBody.message ? errorBody.message : "Calculation failed.",
                    status: "error"
                });

                setOriginalResultDTO(null);

                setResultUnit(null);

                return;
            }

            if (operation === "DIVIDE") {

                const data = await response.json();

                setResult({ text: String(data), status: "ok" });

                setOriginalResultDTO(null);

                setResultUnit(null);

                return;
            }

            const data = await response.json();

            if (data.hasError) {

                setResult({ text: data.errorMessage, status: "error" });

                setOriginalResultDTO(null);

                setResultUnit(null);

                return;
            }

            setResult({
                text: Number(data.value).toFixed(4) + " " + data.unit,
                status: "ok"
            });

            setOriginalResultDTO({
                value: data.value,
                unit: data.unit,
                measurementType: data.measurementType
            });

            setResultUnit(data.unit);

        } catch (error) {

            setResult({ text: error.message, status: "error" });

            setOriginalResultDTO(null);

            setResultUnit(null);
        }

    }

    async function handleResultUnitChange(newUnit) {

        setResultUnit(newUnit);

        if (!originalResultDTO) {

            return;
        }

        const requestBody = {

            thisQuantity: {
                value: originalResultDTO.value,
                unit: originalResultDTO.unit,
                measurementType: originalResultDTO.measurementType
            },

            thatQuantity: {
                value: 1,
                unit: newUnit,
                measurementType: originalResultDTO.measurementType
            }

        };

        try {

            const response = await fetch(CONVERT_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + jwt
                },

                body: JSON.stringify(requestBody)

            });

            if (!response.ok) {

                throw new Error("Unable to convert result.");
            }

            const data = await response.json();

            if (data.hasError) {

                throw new Error(data.errorMessage);
            }

            setResult({
                text: Number(data.value).toFixed(4) + " " + data.unit,
                status: "ok"
            });

        } catch (error) {

            setResult({ text: error.message, status: "error" });
        }

    }

    return (
        <div>

            <div className="input-row">

                <div className="input-group">
                    <label>VALUE 1</label>
                    <input
                        type="number"
                        className={"value-input" + (fieldError && value1 === "" ? " invalid" : "")}
                        placeholder="0"
                        value={value1}
                        onChange={e => setValue1(e.target.value)}
                    />
                    <select
                        className="unit-select"
                        value={unit1}
                        onChange={e => setUnit1(e.target.value)}
                    >
                        {UNITS[measurementType].map(u => (
                            <option key={u} value={u}>{u}</option>
                        ))}
                    </select>
                </div>

                <button
                    className="operator-btn"
                    disabled={isTemperature}
                    onClick={cycleOperator}
                >
                    {OPERATOR_SYMBOLS[operation]}
                </button>

                <div className="input-group">
                    <label>VALUE 2</label>
                    <input
                        type="number"
                        className={"value-input" + (fieldError && value2 === "" ? " invalid" : "")}
                        placeholder="0"
                        value={value2}
                        onChange={e => setValue2(e.target.value)}
                    />
                    <select
                        className="unit-select"
                        value={unit2}
                        onChange={e => setUnit2(e.target.value)}
                    >
                        {UNITS[measurementType].map(u => (
                            <option key={u} value={u}>{u}</option>
                        ))}
                    </select>
                </div>

            </div>

            <div className="operator-label">{operation}</div>

            {isTemperature && (
                <div className="temp-warning show">
                    Temperature does not support arithmetic operations.
                </div>
            )}

            {fieldError && <p className="field-error">{fieldError}</p>}

            <button
                className="calculate-btn"
                disabled={isTemperature}
                onClick={handleCalculate}
            >
                Calculate
            </button>

            {result && (
                <div className={"result-card show" + (result.status === "error" ? " error" : "")}>

                    <div>
                        <div className="result-label">RESULT</div>
                        <div className={"result-value" + (result.status === "error" ? " error" : "")}>
                            {result.text}
                        </div>
                    </div>

                    {originalResultDTO && (
                        <select
                            className="result-unit-select show"
                            value={resultUnit}
                            onChange={e => handleResultUnitChange(e.target.value)}
                        >
                            {UNITS[measurementType].map(u => (
                                <option key={u} value={u}>{u}</option>
                            ))}
                        </select>
                    )}

                </div>
            )}

        </div>
    );
}