const CARD_DATA = [
    { type: "LENGTH", label: "Length", img: "/images/length.png" },
    { type: "WEIGHT", label: "Weight", img: "/images/weight.png" },
    { type: "TEMPERATURE", label: "Temperature", img: "/images/temperature.png" },
    { type: "VOLUME", label: "Volume", img: "/images/volume.png" }
];

const ACTION_DATA = [
    { key: "COMPARISON", label: "Comparison" },
    { key: "CONVERSION", label: "Conversion" },
    { key: "ARITHMETIC", label: "Arithmetic" }
];

export default function Sidebar({
                                    profile,
                                    onLogout,
                                    measurementType,
                                    onSelectType,
                                    action,
                                    onSelectAction
                                }) {

    return (
        <aside className="sidebar">

            {profile && (
                <div className="profile-card">

                    {profile.picture ? (
                        <img className="profile-avatar-img" src={profile.picture} alt={profile.name} />
                    ) : (
                        <div className="profile-avatar-fallback">{profile.initial}</div>
                    )}

                    <div className="profile-name">{profile.name}</div>

                    {profile.email && (
                        <div className="profile-email">{profile.email}</div>
                    )}

                    <button className="profile-logout-btn" onClick={onLogout}>
                        Logout
                    </button>

                </div>
            )}

            <section className="measurement-section">

                <h3>CHOOSE TYPE</h3>

                <div className="card-container">

                    {CARD_DATA.map(card => (
                        <div
                            key={card.type}
                            className={"card" + (measurementType === card.type ? " active" : "")}
                            onClick={() => onSelectType(card.type)}
                        >
                            <img src={card.img} alt={card.label} />
                            <p>{card.label}</p>
                        </div>
                    ))}

                </div>

            </section>

            <section className="action-section">

                <h3>CHOOSE ACTION</h3>

                <div className="action-buttons">

                    {ACTION_DATA.map(item => (
                        <button
                            key={item.key}
                            className={"action-btn" + (action === item.key ? " active" : "")}
                            onClick={() => onSelectAction(item.key)}
                        >
                            {item.label}
                        </button>
                    ))}

                </div>

            </section>

        </aside>
    );
}