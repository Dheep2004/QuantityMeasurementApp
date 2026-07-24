import { useState } from 'react';

export default function RateUsPage({ onBack }) {

    const [rating, setRating] = useState(0);

    const [hoverRating, setHoverRating] = useState(0);

    const [comment, setComment] = useState("");

    const [submitted, setSubmitted] = useState(false);

    function handleSubmit() {

        if (rating === 0) {

            return;
        }

        // No backend endpoint exists yet for storing feedback server-side,
        // so this is saved locally for now (per-browser only).
        try {

            const existing = JSON.parse(localStorage.getItem("feedback") || "[]");

            existing.push({
                rating,
                comment,
                submittedAt: new Date().toISOString()
            });

            localStorage.setItem("feedback", JSON.stringify(existing));

        } catch (error) {

            // Ignore storage errors, still show thanks
        }

        setSubmitted(true);
    }

    if (submitted) {

        return (
            <div className="rate-page">
                <div className="rate-thanks">
                    <div className="rate-thanks-icon">&#127881;</div>
                    <div className="rate-thanks-title">Thanks for the feedback!</div>
                    <p className="rate-thanks-text">
                        You rated us {rating} out of 5.
                    </p>
                    <button
                        className="home-cta"
                        style={{ marginTop: "24px" }}
                        onClick={onBack}
                    >
                        Back
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div className="rate-page">

            <h2 className="rate-title">Rate Your Experience</h2>

            <p className="rate-subtitle">
                Let us know how the Quantity Measurement app is working for you.
            </p>

            <div className="rate-stars">
                {[1, 2, 3, 4, 5].map(star => (
                    <button
                        key={star}
                        className={"rate-star" + (star <= (hoverRating || rating) ? " filled" : "")}
                        onMouseEnter={() => setHoverRating(star)}
                        onMouseLeave={() => setHoverRating(0)}
                        onClick={() => setRating(star)}
                        aria-label={`Rate ${star} stars`}
                    >
                        &#9733;
                    </button>
                ))}
            </div>

            <textarea
                className="rate-textarea"
                placeholder="Anything you'd like us to improve? (optional)"
                value={comment}
                onChange={e => setComment(e.target.value)}
            />

            <button
                className="rate-submit-btn"
                disabled={rating === 0}
                onClick={handleSubmit}
            >
                Submit Feedback
            </button>

        </div>
    );
}