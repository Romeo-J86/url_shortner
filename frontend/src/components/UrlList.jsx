import './UrlList.css';

function UrlList({ urls, loading, onDelete }) {
    const formatDate = (dateString) => {
        return new Date(dateString).toLocaleDateString('en-US', {
            month: 'short',
            day: 'numeric',
            year: 'numeric'
        });
    };

    if (loading) {
        return (
            <div className="url-list-container glass-card">
                <h2>📊 Your URLs</h2>
                <div className="loading-container">
                    <div className="spinner large-spinner"></div>
                    <p>Loading URLs...</p>
                </div>
            </div>
        );
    }

    if (urls.length === 0) {
        return (
            <div className="url-list-container glass-card">
                <h2>📊 Your URLs</h2>
                <div className="empty-state">
                    <div className="empty-icon">🔗</div>
                    <p>No URLs created yet</p>
                    <p className="empty-subtitle">Create your first short URL above</p>
                </div>
            </div>
        );
    }

    return (
        <div className="url-list-container glass-card fade-in">
            <h2>📊 Your URLs ({urls.length})</h2>

            <div className="url-list">
                {urls.map((url) => (
                    <div key={url.id} className="url-item">
                        <div className="url-item-header">
                            <a
                                href={url.shortUrl}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="short-code"
                            >
                                /{url.shortCode}
                            </a>
                            <button
                                className="btn btn-danger btn-small"
                                onClick={() => onDelete(url.shortCode)}
                                title="Delete URL"
                            >
                                🗑️ Delete
                            </button>
                        </div>

                        <a
                            href={url.originalUrl}
                            target="_blank"
                            rel="noopener noreferrer"
                            className="original-url-link"
                        >
                            {url.originalUrl}
                        </a>

                        <div className="url-item-footer">
                            <span className="stat">
                                <span className="stat-icon">👆</span>
                                {url.clickCount} clicks
                            </span>
                            <span className="stat">
                                <span className="stat-icon">📅</span>
                                {formatDate(url.createdAt)}
                            </span>
                            {url.expiresAt && (
                                <span className="stat expire-stat">
                                    <span className="stat-icon">⏰</span>
                                    Expires {formatDate(url.expiresAt)}
                                </span>
                            )}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default UrlList;
