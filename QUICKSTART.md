# URL Shortener - Quick Start

## Start with Docker (Recommended)
```cmd
docker-compose up --build
```
Everything will be ready at: http://localhost

## Start Backend (Terminal 1 - Local Development)
```cmd
cd c:\Users\romeo\Documents\url-shortner\backend
mvn spring-boot:run
```

Backend will be available at: http://localhost:12000

## Start Frontend (Terminal 2)
```cmd
cd c:\Users\romeo\Documents\url-shortner\frontend
npm install
npm run dev
```

Frontend will be available at: http://localhost:5173

## Open Application
Open your browser to: http://localhost:5173

## Test the Application
1. Enter a URL (e.g., https://www.google.com)
2. Click "Shorten URL"
3. Copy the generated short URL
4. Open it in a new tab to test the redirect
5. See the click count increment in the URL list

## API Testing (Optional)
```cmd
# Create a short URL
curl -X POST http://localhost:8080/api/urls -H "Content-Type: application/json" -d "{\"url\": \"https://www.example.com\", \"expiryDays\": 30}"

# Get all URLs
curl http://localhost:8080/api/urls

# Test redirect (replace ABC123 with actual short code)
curl -i http://localhost:8080/ABC123
```
