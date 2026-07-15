// ===============================
// API URLs
// ===============================

const ADD_URL =
    "http://localhost:8080/api/v1/quantities/add";

const SUBTRACT_URL =
    "http://localhost:8080/api/v1/quantities/subtract";

const DIVIDE_URL =
    "http://localhost:8080/api/v1/quantities/divide";

const CONVERT_URL =
    "http://localhost:8080/api/v1/quantities/convert";

// ===============================
// Units (keyed by backend measurementType string)
// ===============================

const units = {

    LENGTH: [
        "FEET",
        "INCHES",
        "YARDS",
        "CENTIMETERS"
    ],

    WEIGHT: [
        "KILOGRAM",
        "GRAM",
        "POUND"
    ],

    TEMPERATURE: [
        "CELSIUS",
        "FAHRENHEIT",
        "KELVIN"
    ],

    VOLUME: [
        "LITRE",
        "MILLILITRE",
        "GALLON"
    ]

};

// ===============================
// Elements
// ===============================

const value1Input =
    document.getElementById("value1");

const value2Input =
    document.getElementById("value2");

const unit1Select =
    document.getElementById("unit1");

const unit2Select =
    document.getElementById("unit2");

const operatorBtn =
    document.getElementById("operatorBtn");

const operatorLabel =
    document.getElementById("operatorLabel");

const calculateBtn =
    document.getElementById("calculateBtn");

const tempWarning =
    document.getElementById("tempWarning");

const resultCard =
    document.getElementById("resultCard");

const resultValue =
    document.getElementById("resultValue");

const resultUnitSelect =
    document.getElementById("resultUnitSelect");

// ===============================
// Measurement Type
// ===============================

const measurementType =
    localStorage.getItem("measurementType") || "LENGTH";

// ===============================
// Operator Cycling
// ===============================

const operators = ["ADD", "SUBTRACT", "DIVIDE"];

const operatorSymbols = {
    ADD: "+",
    SUBTRACT: "\u2212",
    DIVIDE: "\u00F7"
};

let currentOperatorIndex = 0;

function updateOperatorDisplay() {

    const op = operators[currentOperatorIndex];

    operatorBtn.textContent = operatorSymbols[op];

    operatorLabel.textContent = op;
}

operatorBtn.addEventListener("click", () => {

    currentOperatorIndex =
        (currentOperatorIndex + 1) % operators.length;

    updateOperatorDisplay();
});

updateOperatorDisplay();

// ===============================
// Load Unit Dropdowns
// ===============================

function loadUnits() {

    unit1Select.innerHTML = "";

    unit2Select.innerHTML = "";

    units[measurementType].forEach(unit => {

        const option1 =
            document.createElement("option");

        option1.value = unit;

        option1.textContent = unit;

        unit1Select.appendChild(option1);

        const option2 =
            document.createElement("option");

        option2.value = unit;

        option2.textContent = unit;

        unit2Select.appendChild(option2);

    });

}

loadUnits();

// ===============================
// Disable Arithmetic for Temperature
// ===============================

if (measurementType === "TEMPERATURE") {

    tempWarning.classList.add("show");

    operatorBtn.disabled = true;

    calculateBtn.disabled = true;

}

// ===============================
// Track original Add/Subtract result
// (source of truth for result-unit chaining)
// ===============================

let originalResultDTO = null;

// ===============================
// Calculate
// ===============================

calculateBtn.addEventListener("click", async () => {

    if (value1Input.value === "" || value2Input.value === "") {

        alert("Please enter both values.");

        return;
    }

    const token =
        localStorage.getItem("jwt");

    const operation =
        operators[currentOperatorIndex];

    const requestBody = {

        thisQuantity: {
            value: Number(value1Input.value),
            unit: unit1Select.value,
            measurementType: measurementType
        },

        thatQuantity: {
            value: Number(value2Input.value),
            unit: unit2Select.value,
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

        const response =
            await fetch(url, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },

                body: JSON.stringify(requestBody)

            });

        const isOk = response.ok;

        resultCard.classList.add("show");

        resultCard.classList.remove("error");

        resultValue.classList.remove("error");

        if (!isOk) {

            const errorBody = await response.json().catch(() => null);

            resultCard.classList.add("error");

            resultValue.classList.add("error");

            resultValue.textContent =
                errorBody && errorBody.message
                    ? errorBody.message
                    : "Calculation failed.";

            resultUnitSelect.classList.remove("show");

            originalResultDTO = null;

            return;
        }

        if (operation === "DIVIDE") {

            const result = await response.json();

            resultValue.textContent = result;

            resultUnitSelect.classList.remove("show");

            originalResultDTO = null;

            return;
        }

        const result = await response.json();

        if (result.hasError) {

            resultCard.classList.add("error");

            resultValue.classList.add("error");

            resultValue.textContent = result.errorMessage;

            resultUnitSelect.classList.remove("show");

            originalResultDTO = null;

            return;
        }

        originalResultDTO = {
            value: result.value,
            unit: result.unit,
            measurementType: result.measurementType
        };

        displayResult(result.value, result.unit);

        populateResultUnitSelect(result.unit);

    } catch (error) {

        resultCard.classList.add("show", "error");

        resultValue.classList.add("error");

        resultValue.textContent = error.message;

        resultUnitSelect.classList.remove("show");

        originalResultDTO = null;

    }

});

// ===============================
// Result Display Helper
// ===============================

function displayResult(value, unit) {

    resultValue.textContent =
        Number(value).toFixed(4) + " " + unit;

}

// ===============================
// Result Unit Dropdown
// ===============================

function populateResultUnitSelect(selectedUnit) {

    resultUnitSelect.innerHTML = "";

    units[measurementType].forEach(unit => {

        const option =
            document.createElement("option");

        option.value = unit;

        option.textContent = unit;

        if (unit === selectedUnit) {

            option.selected = true;
        }

        resultUnitSelect.appendChild(option);

    });

    resultUnitSelect.classList.add("show");

}

resultUnitSelect.addEventListener("change", async () => {

    if (!originalResultDTO) {

        return;
    }

    const token =
        localStorage.getItem("jwt");

    const requestBody = {

        thisQuantity: {
            value: originalResultDTO.value,
            unit: originalResultDTO.unit,
            measurementType: originalResultDTO.measurementType
        },

        thatQuantity: {
            value: 1,
            unit: resultUnitSelect.value,
            measurementType: originalResultDTO.measurementType
        }

    };

    try {

        const response =
            await fetch(CONVERT_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },

                body: JSON.stringify(requestBody)

            });

        if (!response.ok) {

            throw new Error("Unable to convert result.");
        }

        const result = await response.json();

        if (result.hasError) {

            throw new Error(result.errorMessage);
        }

        displayResult(result.value, result.unit);

    } catch (error) {

        resultValue.textContent = error.message;

    }

});