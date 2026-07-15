// ===============================
// API URL
// ===============================

const API_URL =
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

const sourceValueInput =
    document.getElementById("sourceValue");

const sourceUnitSelect =
    document.getElementById("sourceUnit");

const targetUnitSelect =
    document.getElementById("targetUnit");

const convertBtn =
    document.getElementById("convertBtn");

const resultCard =
    document.getElementById("resultCard");

const resultValue =
    document.getElementById("resultValue");

// ===============================
// Measurement Type (set by parent window via localStorage)
// ===============================

const measurementType =
    localStorage.getItem("measurementType") || "LENGTH";

// ===============================
// Load Unit Dropdowns
// ===============================

function loadUnits() {

    sourceUnitSelect.innerHTML = "";

    targetUnitSelect.innerHTML = "";

    units[measurementType].forEach(unit => {

        const option1 =
            document.createElement("option");

        option1.value = unit;

        option1.textContent = unit;

        sourceUnitSelect.appendChild(option1);

        const option2 =
            document.createElement("option");

        option2.value = unit;

        option2.textContent = unit;

        targetUnitSelect.appendChild(option2);

    });

    // Default target to a different unit than source, if possible
    if (targetUnitSelect.options.length > 1) {

        targetUnitSelect.selectedIndex = 1;
    }

}

loadUnits();

// ===============================
// Convert
// ===============================

convertBtn.addEventListener("click", async () => {

    if (sourceValueInput.value === "") {

        alert("Please enter a value.");

        return;
    }

    const token =
        localStorage.getItem("jwt");

    const requestBody = {

        thisQuantity: {
            value: Number(sourceValueInput.value),
            unit: sourceUnitSelect.value,
            measurementType: measurementType
        },

        thatQuantity: {
            value: 1,
            unit: targetUnitSelect.value,
            measurementType: measurementType
        }

    };

    try {

        const response =
            await fetch(API_URL, {

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

        if (isOk) {

            const result = await response.json();

            if (result.hasError) {

                resultCard.classList.add("error");

                resultValue.classList.add("error");

                resultValue.textContent = result.errorMessage;

            } else {

                resultValue.textContent =
                    result.value.toFixed(4) + " " + result.unit;
            }

        } else {

            const errorBody = await response.json().catch(() => null);

            resultCard.classList.add("error");

            resultValue.classList.add("error");

            resultValue.textContent =
                errorBody && errorBody.message
                    ? errorBody.message
                    : "Conversion failed.";
        }

    } catch (error) {

        resultCard.classList.add("show", "error");

        resultValue.classList.add("error");

        resultValue.textContent = error.message;

    }

});