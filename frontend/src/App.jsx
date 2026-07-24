import { useEffect, useState } from 'react';
import Header from './components/Header.jsx';
import Sidebar from './components/Sidebar.jsx';
import ComparisonPanel from './components/ComparisonPanel.jsx';
import ConversionPanel from './components/ConversionPanel.jsx';
import ArithmeticPanel from './components/ArithmeticPanel.jsx';
import HistorySection from './components/HistorySection.jsx';
import HomePage from './components/HomePage.jsx';
import RateUsPage from './components/RateUsPage.jsx';
import { UNITS } from './constants/units.js';

const VALID_TYPES = Object.keys(UNITS);

function getInitialMeasurementType() {

    const stored = localStorage.getItem("measurementType");

    if (stored && VALID_TYPES.includes(stored)) {

        return stored;
    }

    return "LENGTH";
}

function decodeJwt(token) {

    try {

        const payload = token.split(".")[1];

        const decoded = decodeURIComponent(

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

export default function App() {

    const [jwt, setJwt] = useState(null);

    const [profile, setProfile] = useState(null);

    const [measurementType, setMeasurementType] = useState(
        getInitialMeasurementType()
    );

    const [action, setAction] = useState("ARITHMETIC");

    // 'home' | 'rate' | 'dashboard'
    const [view, setView] = useState("home");

    const [darkMode, setDarkMode] = useState(
        localStorage.getItem("darkMode") === "true"
    );

    useEffect(() => {

        document.body.classList.toggle("dark", darkMode);

        localStorage.setItem("darkMode", String(darkMode));

    }, [darkMode]);

    useEffect(() => {

        const urlParams = new URLSearchParams(window.location.search);

        const tokenFromUrl = urlParams.get("token");

        if (tokenFromUrl) {

            localStorage.setItem("jwt", tokenFromUrl);

            window.history.replaceState(
                {},
                document.title,
                window.location.pathname
            );
        }

        const storedJwt = localStorage.getItem("jwt");

        if (storedJwt) {

            setJwt(storedJwt);

            setView("dashboard");

            const claims = decodeJwt(storedJwt);

            if (claims && claims.name) {

                setProfile({
                    name: claims.name,
                    initial: claims.name.trim().charAt(0),
                    email: claims.sub || "",
                    picture: claims.picture || ""
                });
            }
        }

    }, []);

    function selectMeasurementType(type) {

        setMeasurementType(type);

        localStorage.setItem("measurementType", type);
    }

    function handleLogin() {

        window.location.href = "/api/auth/login";
    }

    function handleLogout() {

        localStorage.removeItem("jwt");

        localStorage.removeItem("measurementType");

        window.location.href = "/api/auth/logout";
    }

    const isAuthenticated = Boolean(jwt);

    return (
        <div className="container">

            <Header
                view={view}
                onNavigate={setView}
                isAuthenticated={isAuthenticated}
                onLogin={handleLogin}
                darkMode={darkMode}
                onToggleDarkMode={() => setDarkMode(!darkMode)}
            />

            {view === "home" && (
                <HomePage
                    isAuthenticated={isAuthenticated}
                    onGetStarted={() => isAuthenticated ? setView("dashboard") : handleLogin()}
                    onRateUs={() => setView("rate")}
                />
            )}

            {view === "rate" && (
                <RateUsPage onBack={() => setView(isAuthenticated ? "dashboard" : "home")} />
            )}

            {view === "dashboard" && isAuthenticated && (
                <div className="layout">

                    <Sidebar
                        profile={profile}
                        onLogout={handleLogout}
                        measurementType={measurementType}
                        onSelectType={selectMeasurementType}
                        action={action}
                        onSelectAction={setAction}
                    />

                    <main className="main-content">

                        <section className="panel-section">

                            {action === "COMPARISON" && (
                                <ComparisonPanel jwt={jwt} measurementType={measurementType} />
                            )}

                            {action === "CONVERSION" && (
                                <ConversionPanel jwt={jwt} measurementType={measurementType} />
                            )}

                            {action === "ARITHMETIC" && (
                                <ArithmeticPanel jwt={jwt} measurementType={measurementType} />
                            )}

                        </section>

                        <HistorySection jwt={jwt} />

                    </main>

                </div>
            )}

        </div>
    );
}