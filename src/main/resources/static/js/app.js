// ==========================================
// JWT Capture From URL (OAuth redirect)
// ==========================================

const urlParams =
    new URLSearchParams(window.location.search);

const tokenFromUrl =
    urlParams.get("token");

if (tokenFromUrl) {

    localStorage.setItem("jwt", tokenFromUrl);

    window.history.replaceState(
        {},
        document.title,
        window.location.pathname
    );
}

const jwt =
    localStorage.getItem("jwt");

// ==========================================
// Redirect to login if no token present
// ==========================================

if (!jwt) {

    window.location.href = "/api/auth/login";

}

// ==========================================
// Decode JWT payload (name, picture claims)
// ==========================================

function decodeJwt(token) {

    try {

        const payload =
            token.split(".")[1];

        const decoded =
            decodeURIComponent(

                atob(
                    payload
                        .replace(/-/g, "+")
                        .replace(/_/g, "/")
                )
                    .split("")
                    .map(c =>
                        "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2)
                    )
                    .join("")
            );

        return JSON.parse(decoded);

    } catch (error) {

        return null;
    }
}

// ==========================================
// Populate Profile Chip
// ==========================================

const userProfile =
    document.getElementById("userProfile");

const userAvatar =
    document.getElementById("userAvatar");

const userName =
    document.getElementById("userName");

const logoutBtn =
    document.getElementById("logoutBtn");

if (jwt) {

    const claims = decodeJwt(jwt);

    if (claims && claims.name) {

        userName.textContent = claims.name;

        userAvatar.textContent =
            claims.name.trim().charAt(0);

        userProfile.style.display = "flex";
    }
}

logoutBtn.addEventListener("click", () => {

    localStorage.removeItem("jwt");

    localStorage.removeItem("measurementType");

    window.location.href = "/api/auth/logout";

});

// ==========================================
// Measurement Cards
// ==========================================

const lengthCard =
    document.getElementById("lengthCard");

const weightCard =
    document.getElementById("weightCard");

const temperatureCard =
    document.getElementById("temperatureCard");

const volumeCard =
    document.getElementById("volumeCard");

const allCards =
    [lengthCard, weightCard, temperatureCard, volumeCard];

const contentFrame =
    document.getElementById("contentFrame");

if (!localStorage.getItem("measurementType")) {

    localStorage.setItem("measurementType", "Length");
}

function selectCard(card, measurementType) {

    allCards.forEach(c =>
        c.classList.remove("active")
    );

    card.classList.add("active");

    localStorage.setItem(
        "measurementType",
        measurementType
    );

    // Reload current iframe page so its unit dropdowns refresh
    contentFrame.src = contentFrame.src;
}

lengthCard.addEventListener("click", () =>
    selectCard(lengthCard, "LENGTH")
);

weightCard.addEventListener("click", () =>
    selectCard(weightCard, "WEIGHT")
);

temperatureCard.addEventListener("click", () =>
    selectCard(temperatureCard, "TEMPERATURE")
);

volumeCard.addEventListener("click", () =>
    selectCard(volumeCard, "VOLUME")
);

// ==========================================
// Action Buttons + iframe Switching
// ==========================================

const comparisonBtn =
    document.getElementById("comparisonBtn");

const conversionBtn =
    document.getElementById("conversionBtn");

const arithmeticBtn =
    document.getElementById("arithmeticBtn");

const allActionButtons =
    [comparisonBtn, conversionBtn, arithmeticBtn];

function selectAction(button, page) {

    allActionButtons.forEach(b =>
        b.classList.remove("active")
    );

    button.classList.add("active");

    contentFrame.src = page;
}

comparisonBtn.addEventListener("click", () =>
    selectAction(comparisonBtn, "pages/comparison.html")
);

conversionBtn.addEventListener("click", () =>
    selectAction(conversionBtn, "pages/conversion.html")
);

arithmeticBtn.addEventListener("click", () =>
    selectAction(arithmeticBtn, "pages/arithmetic.html")
);

// ==========================================
// History Section
// ==========================================

const HISTORY_BASE_URL =
    "http://localhost:8080/api/v1/quantities/history";

const historyToggle =
    document.getElementById("historyToggle");

const historyChevron =
    document.getElementById("historyChevron");

const historyBody =
    document.getElementById("historyBody");

const historyTabs =
    document.getElementById("historyTabs");

const historyCount =
    document.getElementById("historyCount");

const historyTableBody =
    document.getElementById("historyTableBody");

const historyEmpty =
    document.getElementById("historyEmpty");

let activeOperation = "ADD";

historyToggle.addEventListener("click", () => {

    historyBody.classList.toggle("collapsed");

    historyChevron.classList.toggle("collapsed");
});

historyTabs.querySelectorAll(".tab-btn").forEach(tab => {

    tab.addEventListener("click", () => {

        historyTabs.querySelectorAll(".tab-btn").forEach(t =>
            t.classList.remove("active")
        );

        tab.classList.add("active");

        activeOperation = tab.getAttribute("data-op");

        loadHistory(activeOperation);
    });
});

async function loadHistory(operation) {

    const url =
        operation === "ERRORS"
            ? HISTORY_BASE_URL + "/errors"
            : HISTORY_BASE_URL + "/operation/" + operation;

    try {

        const response =
            await fetch(url, {

                method: "GET",

                headers: {
                    "Authorization": "Bearer " + jwt
                }
            });

        if (!response.ok) {

            throw new Error("Failed to load history.");
        }

        const records =
            await response.json();

        renderHistory(records);

    } catch (error) {

        historyTableBody.innerHTML = "";

        historyEmpty.style.display = "block";

        historyEmpty.textContent = error.message;

        historyCount.textContent = "0 records";
    }
}

function renderHistory(records) {

    historyTableBody.innerHTML = "";

    if (!records || records.length === 0) {

        historyEmpty.style.display = "block";

        historyCount.textContent = "0 records";

        return;
    }

    historyEmpty.style.display = "none";

    historyCount.textContent =
        records.length + (records.length === 1 ? " record" : " records");

    records.forEach(record => {

        const row =
            document.createElement("tr");

        const resultText =
            record.error
                ? record.errorMessage
                : record.result;

        row.innerHTML =
            "<td>" + record.operation + "</td>" +
            "<td>" + record.firstValue + " " + record.firstUnit + "</td>" +
            "<td>" + record.secondValue + " " + record.secondUnit + "</td>" +
            "<td>" + resultText + "</td>";

        historyTableBody.appendChild(row);
    });
}

if (jwt) {

    loadHistory(activeOperation);
}