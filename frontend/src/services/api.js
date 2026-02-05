import axios from 'axios';

// Use environment variable for API URL in production, localhost for development
const API_BASE_URL = import.meta.env.VITE_API_URL
    ? `${import.meta.env.VITE_API_URL}/api`
    : 'http://localhost:12000/api';

export const urlApi = {
    // Create a short URL
    createShortUrl: async (url, expiryDays = null) => {
        const response = await axios.post(`${API_BASE_URL}/urls`, {
            url,
            expiryDays
        });
        return response.data;
    },

    // Get all URLs (paginated)
    getAllUrls: async (page = 0, size = 10) => {
        const response = await axios.get(`${API_BASE_URL}/urls`, {
            params: { page, size }
        });
        return response.data;
    },

    // Get URL details
    getUrlDetails: async (shortCode) => {
        const response = await axios.get(`${API_BASE_URL}/urls/${shortCode}`);
        return response.data;
    },

    // Delete a URL
    deleteUrl: async (shortCode) => {
        await axios.delete(`${API_BASE_URL}/urls/${shortCode}`);
    }
};
