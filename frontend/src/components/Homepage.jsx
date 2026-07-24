export default function HomePage({ isAuthenticated, onGetStarted, onRateUs }) {

    return (
        <div className="home-page">

            <span className="home-eyebrow">LENGTH &middot; WEIGHT &middot; TEMPERATURE &middot; VOLUME</span>

            <h2 className="home-title">
                Measure, Compare and Convert<br />Quantities in Seconds
            </h2>

            <p className="home-subtitle">
                Compare two values, convert between units, or run arithmetic
                across Length, Weight, Temperature and Volume &mdash; every
                calculation runs on the real backend, with your full history
                saved and searchable.
            </p>

            <button className="home-cta" onClick={onGetStarted}>
                {isAuthenticated ? "Go to Dashboard" : "Get Started with Google"}
            </button>

            <div className="home-features">

                <div className="home-feature-card">
                    <div className="home-feature-icon">&#9878;</div>
                    <div className="home-feature-title">Comparison</div>
                    <div className="home-feature-desc">
                        Check whether two quantities are equal, across any supported unit.
                    </div>
                </div>

                <div className="home-feature-card">
                    <div className="home-feature-icon">&#8646;</div>
                    <div className="home-feature-title">Conversion</div>
                    <div className="home-feature-desc">
                        Convert a value into any target unit within the same measurement type.
                    </div>
                </div>

                <div className="home-feature-card">
                    <div className="home-feature-icon">&#10133;</div>
                    <div className="home-feature-title">Arithmetic</div>
                    <div className="home-feature-desc">
                        Add, subtract or divide two quantities, then view the result in any unit.
                    </div>
                </div>

                <div className="home-feature-card">
                    <div className="home-feature-icon">&#128337;</div>
                    <div className="home-feature-title">History</div>
                    <div className="home-feature-desc">
                        Every calculation is saved &mdash; filter by operation or view errors.
                    </div>
                </div>

            </div>

            <p style={{ marginTop: "40px" }}>
                <button
                    className="nav-link"
                    style={{ color: "#4f46e5", background: "var(--surface-alt)", border: "1px solid var(--border)" }}
                    onClick={onRateUs}
                >
                    Enjoying the app? Rate us &rarr;
                </button>
            </p>

        </div>
    );
}