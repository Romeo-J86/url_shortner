import { useState, useEffect } from 'react';
import './App.css';
import UrlShortenerForm from './components/UrlShortenerForm';
import ShortenedUrlDisplay from './components/ShortenedUrlDisplay';
import UrlList from './components/UrlList';
import { urlApi } from './services/api';

function App() {
    const [shortenedUrl, setShortenedUrl] = useState(null);
    const [urls, setUrls] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchUrls();
    }, []);

    const fetchUrls = async () => {
        try {
            setLoading(true);
            const data = await urlApi.getAllUrls();
            // Handle Spring Data Page object or direct array
            setUrls(data.content || (Array.isArray(data) ? data : []));
        } catch (error) {
            console.error('Error fetching URLs:', error);
            setUrls([]);
        } finally {
            setLoading(false);
        }
    };

    const handleUrlShortened = (newUrl) => {
        setShortenedUrl(newUrl);
        fetchUrls(); // Refresh the list
    };

    const handleCreateAnother = () => {
        setShortenedUrl(null);
    };

    const handleDelete = async (shortCode) => {
        try {
            await urlApi.deleteUrl(shortCode);
            fetchUrls(); // Refresh the list
        } catch (error) {
            console.error('Error deleting URL:', error);
            alert('Failed to delete URL');
        }
    };

    return (
        <div className="app">
            <div className="container">
                <header className="header">
                    <h1>⚡ URL Shortener</h1>
                    <p className="subtitle">Transform long URLs into short, shareable links</p>
                </header>

                <main className="main-content">
                    {!shortenedUrl ? (
                        <UrlShortenerForm onUrlShortened={handleUrlShortened} />
                    ) : (
                        <ShortenedUrlDisplay
                            urlData={shortenedUrl}
                            onCreateAnother={handleCreateAnother}
                        />
                    )}

                    <div className="divider"></div>

                    <UrlList
                        urls={urls}
                        loading={loading}
                        onDelete={handleDelete}
                    />
                </main>

                <footer className="footer">
                    <p>Built with React, Spring Boot & Java 17</p>
                </footer>
            </div>
        </div>
    );
}

export default App;
