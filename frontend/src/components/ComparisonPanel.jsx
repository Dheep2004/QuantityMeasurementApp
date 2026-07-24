import { useEffect, useState } from 'react';
import { UNITS } from '../constants/units.js';
import { COMPARE_URL } from '../constants/api.js';

export default function ComparisonPanel({ jwt, measurementType }) {

    const [value1, setValue1] = useState("");

    const [value2, setValue2] = useState("");

    const [unit1, setUnit1] = useState(UNITS[measurementType][0]);

    const [unit2, setUnit2] = useState(UNITS[measurementType][0]);

    const [result, setResult] = useState(null);

    const [fieldError, setFieldError] = useState("");

    useEffect(() => {

        setUnit1(UNITS[measurementType][0]);

        setUnit2(UNITS[measurementType][0]);

        setResult(null);

        setFieldError("");

    }, [measurementType]);

    async function handleCompare() {

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

        try {

            const response = await fetch(COMPARE_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + jwt
                },

                body: JSON.stringify(requestBody)

            });

            if (response.ok) {

                const data = await response.json();

                setResult({
                    text: data ? "Equal" : "Not Equal",
                    status: data ? "equal" : "not-equal"
                });

            } else {

                const errorBody = await response.json().catch(() => null);

                setResult({
                    text: errorBody && errorBody.message ? errorBody.message : "Comparison failed.",
                    status: "error"
                });
            }

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

            {fieldError && <p className="field-error">{fieldError}</p>}

            <button className="compare-btn" onClick={handleCompare}>Compare</button>

            {result && (
                <div className={"result-card show" + (result.status === "error" ? " error" : "")}>
                    <div className="result-label">RESULT</div>
                    <div className={"result-value " + result.status}>{result.text}</div>
                </div>
            )}

        </div>
    );
}