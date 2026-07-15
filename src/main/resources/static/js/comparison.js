// ===============================
// API URL
// ===============================

const API_URL =
    "http://localhost:8080/api/v1/quantities/compare";

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

const compareBtn =
    document.getElementById("compareBtn");

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
// Compare
// ===============================

compareBtn.addEventListener("click", async () => {

    if (value1Input.value === "" || value2Input.value === "") {

        alert("Please enter both values.");

        return;
    }

    const token =
        localStorage.getItem("jwt");

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

        let result;

        if (isOk) {

            result = await response.json();

        } else {

            const errorBody = await response.json().catch(() => null);

            result = errorBody && errorBody.message
                ? errorBody.message
                : "Comparison failed.";
        }

        resultCard.classList.add("show");

        resultCard.classList.remove("error");

        resultValue.classList.remove("equal", "not-equal", "error");

        if (isOk) {

            resultValue.textContent = result ? "Equal" : "Not Equal";

            resultValue.classList.add(result ? "equal" : "not-equal");

        } else {

            resultCard.classList.add("error");

            resultValue.classList.add("error");

            resultValue.textContent = result;
        }

    } catch (error) {

        resultCard.classList.add("show", "error");

        resultValue.classList.add("error");

        resultValue.textContent = error.message;

    }

});