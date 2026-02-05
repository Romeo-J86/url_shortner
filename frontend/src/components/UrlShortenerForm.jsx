import { useState } from 'react';
import { urlApi } from '../services/api';
import './UrlShortenerForm.css';

function UrlShortenerForm({ onUrlShortened }) {
    const [url, setUrl] = useState('');
    const [expiryDays, setExpiryDays] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        if (!url.trim()) {
            setError('Please enter a URL');
            return;
        }

        if (!url.match(/^https?:\/\/.+/)) {
            setError('URL must start with http:// or https://');
            return;
        }

        try {
            setLoading(true);
            const expiryValue = expiryDays ? parseInt(expiryDays) : null;
            const result = await urlApi.createShortUrl(url, expiryValue);
            onUrlShortened(result);
            setUrl('');
            setExpiryDays('');
        } catch (err) {
            console.error('Error creating short URL:', err);
            setError(err.response?.data?.message || 'Failed to create short URL');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="url-form-container glass-card fade-in-up">
            <h2>✨ Shorten Your URL</h2>

            <form onSubmit={handleSubmit} className="url-form">
                <div className="input-group">
                    <label htmlFor="url">Enter Long URL</label>
                    <input
                        type="text"
                        id="url"
                        value={url}
                        onChange={(e) => setUrl(e.target.value)}
                        placeholder="https://example.com/very-long-url"
                        disabled={loading}
                    />
                </div>

                <div className="input-group">
                    <label htmlFor="expiry">Expiry (Days) - Optional</label>
                    <input
                        type="number"
                        id="expiry"
                        value={expiryDays}
                        onChange={(e) => setExpiryDays(e.target.value)}
                        placeholder="Leave empty for no expiry"
                        min="1"
                        disabled={loading}
                    />
                </div>

                {error && (
                    <div className="error-message">
                        ⚠️ {error}
                    </div>
                )}

                <button
                    type="submit"
                    className="btn btn-primary btn-large"
                    disabled={loading}
                >
                    {loading ? (
                        <>
                            <div className="spinner"></div>
                            Shortening...
                        </>
                    ) : (
                        <>
                            🚀 Shorten URL
                        </>
                    )}
                </button>
            </form>
        </div>
    );
}

export default UrlShortenerForm;
