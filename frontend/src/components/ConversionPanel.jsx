import { useEffect, useState } from 'react';
import { UNITS } from '../constants/units.js';
import { CONVERT_URL } from '../constants/api.js';

export default function ConversionPanel({ jwt, measurementType }) {

    const [sourceValue, setSourceValue] = useState("");

    const [sourceUnit, setSourceUnit] = useState(UNITS[measurementType][0]);

    const [targetUnit, setTargetUnit] = useState(
        UNITS[measurementType][1] || UNITS[measurementType][0]
    );

    const [result, setResult] = useState(null);

    const [fieldError, setFieldError] = useState("");

    useEffect(() => {

        setSourceUnit(UNITS[measurementType][0]);

        setTargetUnit(UNITS[measurementType][1] || UNITS[measurementType][0]);

        setResult(null);

        setFieldError("");

    }, [measurementType]);

    async function handleConvert() {

        if (sourceValue === "") {

            setFieldError("Please enter a value.");

            return;
        }

        setFieldError("");

        const requestBody = {

            thisQuantity: {
                value: Number(sourceValue),
                unit: sourceUnit,
                measurementType: measurementType
            },

            thatQuantity: {
                value: 1,
                unit: targetUnit,
                measurementType: measurementType
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

            if (response.ok) {

                const data = await response.json();

                if (data.hasError) {

                    setResult({ text: data.errorMessage, status: "error" });

                } else {

                    setResult({
                        text: Number(data.value).toFixed(4) + " " + data.unit,
                        status: "ok"
                    });
                }

            } else {

                const errorBody = await response.json().catch(() => null);

                setResult({
                    text: errorBody && errorBody.message ? errorBody.message : "Conversion failed.",
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
                    <label>VALUE</label>
                    <input
                        type="number"
                        className={"value-input" + (fieldError ? " invalid" : "")}
                        placeholder="0"
                        value={sourceValue}
                        onChange={e => setSourceValue(e.target.value)}
                    />
                    <select
                        className="unit-select"
                        value={sourceUnit}
                        onChange={e => setSourceUnit(e.target.value)}
                    >
                        {UNITS[measurementType].map(u => (
                            <option key={u} value={u}>{u}</option>
                        ))}
                    </select>
                </div>

                <div className="arrow-divider">&#8594;</div>

                <div className="input-group">
                    <label>CONVERT TO</label>
                    <div style={{ height: "52px" }}></div>
                    <select
                        className="unit-select"
                        value={targetUnit}
                        onChange={e => setTargetUnit(e.target.value)}
                    >
                        {UNITS[measurementType].map(u => (
                            <option key={u} value={u}>{u}</option>
                        ))}
                    </select>
                </div>

            </div>

            {fieldError && <p className="field-error">{fieldError}</p>}

            <button className="convert-btn" onClick={handleConvert}>Convert</button>

            {result && (
                <div className={"result-card show" + (result.status === "error" ? " error" : "")}>
                    <div className="result-label">RESULT</div>
                    <div className={"result-value" + (result.status === "error" ? " error" : "")}>
                        {result.text}
                    </div>
                </div>
            )}

        </div>
    );
}