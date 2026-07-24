export default function Header({
                                   view,
                                   onNavigate,
                                   isAuthenticated,
                                   onLogin,
                                   darkMode,
                                   onToggleDarkMode
                               }) {

    return (
        <header className="header">

            <div className="header-left">

                <h1 onClick={() => onNavigate("home")} style={{ cursor: "pointer" }}>
                    Welcome To Quantity Measurement
                </h1>

                <nav className="header-nav">

                    <button
                        className={"nav-link" + (view === "home" ? " active" : "")}
                        onClick={() => onNavigate("home")}
                    >
                        Home
                    </button>

                    {isAuthenticated && (
                        <button
                            className={"nav-link" + (view === "dashboard" ? " active" : "")}
                            onClick={() => onNavigate("dashboard")}
                        >
                            Dashboard
                        </button>
                    )}

                    <button
                        className={"nav-link" + (view === "rate" ? " active" : "")}
                        onClick={() => onNavigate("rate")}
                    >
                        Rate Us
                    </button>

                </nav>

            </div>

            <div className="header-right">

                <button
                    className="theme-toggle-btn"
                    title={darkMode ? "Switch to light mode" : "Switch to dark mode"}
                    onClick={onToggleDarkMode}
                >
                    {darkMode ? "\u2600" : "\u263D"}
                </button>

                {!isAuthenticated && (
                    <button className="login-btn" onClick={onLogin}>
                        Login with Google
                    </button>
                )}

            </div>

        </header>
    );
}