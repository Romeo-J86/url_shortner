import { useState } from 'react';
import './ShortenedUrlDisplay.css';

function ShortenedUrlDisplay({ urlData, onCreateAnother }) {
    const [copied, setCopied] = useState(false);

    const handleCopy = async () => {
        try {
            await navigator.clipboard.writeText(urlData.shortUrl);
            setCopied(true);
            setTimeout(() => setCopied(false), 2000);
        } catch (err) {
            console.error('Failed to copy:', err);
        }
    };

    const formatDate = (dateString) => {
        return new Date(dateString).toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    };

    return (
        <div className="shortened-url-container glass-card fade-in-up">
            <div className="success-icon">✅</div>

            <h2>Your Short URL is Ready!</h2>

            <div className="short-url-display">
                <div className="url-box">
                    <span className="url-text">{urlData.shortUrl}</span>
                    <button
                        className="btn btn-secondary copy-btn"
                        onClick={handleCopy}
                    >
                        {copied ? '✓ Copied!' : '📋 Copy'}
                    </button>
                </div>
            </div>

            <div className="url-details">
                <div className="detail-item">
                    <span className="detail-label">Original URL:</span>
                    <a
                        href={urlData.originalUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="detail-value original-url"
                    >
                        {urlData.originalUrl}
                    </a>
                </div>

                <div className="detail-grid">
                    <div className="detail-item">
                        <span className="detail-label">Created:</span>
                        <span className="detail-value">{formatDate(urlData.createdAt)}</span>
                    </div>

                    {urlData.expiresAt && (
                        <div className="detail-item">
                            <span className="detail-label">Expires:</span>
                            <span className="detail-value">{formatDate(urlData.expiresAt)}</span>
                        </div>
                    )}

                    <div className="detail-item">
                        <span className="detail-label">Clicks:</span>
                        <span className="detail-value">{urlData.clickCount}</span>
                    </div>
                </div>
            </div>

            <button
                className="btn btn-primary btn-large"
                onClick={onCreateAnother}
            >
                ➕ Create Another
            </button>
        </div>
    );
}

export default ShortenedUrlDisplay;
