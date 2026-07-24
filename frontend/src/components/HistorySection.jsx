import { useEffect, useState } from 'react';
import { HISTORY_URL } from '../constants/api.js';

const TABS = [
    { key: "ADD", label: "Add" },
    { key: "SUBTRACT", label: "Subtract" },
    { key: "DIVIDE", label: "Divide" },
    { key: "CONVERT", label: "Convert" },
    { key: "COMPARE", label: "Compare" },
    { key: "ERRORS", label: "Errors" }
];

export default function HistorySection({ jwt }) {

    const [collapsed, setCollapsed] = useState(false);

    const [activeOperation, setActiveOperation] = useState("ADD");

    const [records, setRecords] = useState([]);

    const [errorMessage, setErrorMessage] = useState(null);

    async function loadHistory(operation) {

        const url =
            operation === "ERRORS"
                ? HISTORY_URL + "/errors"
                : HISTORY_URL + "/operation/" + operation;

        try {

            const response = await fetch(url, {

                method: "GET",

                headers: {
                    "Authorization": "Bearer " + jwt
                }

            });

            if (!response.ok) {

                throw new Error("Failed to load history.");
            }

            const data = await response.json();

            setRecords(data || []);

            setErrorMessage(null);

        } catch (error) {

            setRecords([]);

            setErrorMessage(error.message);
        }

    }

    useEffect(() => {

        loadHistory(activeOperation);

        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [activeOperation]);

    return (
        <section className="history-section">

            <div
                className="history-toggle"
                onClick={() => setCollapsed(!collapsed)}
            >
                <h3>View History</h3>
                <span className={"chevron" + (collapsed ? " collapsed" : "")}>&#9650;</span>
            </div>

            <div className={"history-body" + (collapsed ? " collapsed" : "")}>

                <div className="history-tabs">

                    {TABS.map(tab => (
                        <button
                            key={tab.key}
                            className={"tab-btn" + (activeOperation === tab.key ? " active" : "")}
                            onClick={() => setActiveOperation(tab.key)}
                        >
                            {tab.label}
                        </button>
                    ))}

                </div>

                <p className="history-count">
                    {errorMessage
                        ? "0 records"
                        : records.length + (records.length === 1 ? " record" : " records")}
                </p>

                {!errorMessage && records.length > 0 && (
                    <table className="history-table">
                        <thead>
                        <tr>
                            <th>OP</th>
                            <th>THIS</th>
                            <th>THAT</th>
                            <th>RESULT</th>
                        </tr>
                        </thead>
                        <tbody>
                        {records.map((record, index) => (
                            <tr key={index}>
                                <td>{record.operation}</td>
                                <td>{record.firstValue} {record.firstUnit}</td>
                                <td>{record.secondValue} {record.secondUnit}</td>
                                <td>{record.error ? record.errorMessage : record.result}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}

                {(errorMessage || records.length === 0) && (
                    <p className="history-empty">
                        {errorMessage || "No records found."}
                    </p>
                )}

            </div>

        </section>
    );
}