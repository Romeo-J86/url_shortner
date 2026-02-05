# URL Shortener Service

A modern, full-stack URL shortening service built with **Spring Boot (Java 17)** backend and **React** frontend.

## Features

✨ **Core Functionality**
- Shorten long URLs into compact, shareable links
- Automatic redirect from short codes to original URLs
- Optional expiry dates for time-limited links
- Click tracking and analytics
- Full CRUD operations

🎨 **Modern UI Design**
- Dark theme with animated gradient background
- Glassmorphism effects
- Smooth animations and transitions
- Fully responsive (mobile-friendly)
- Copy-to-clipboard functionality

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.3.3
- **Java Version**: 17
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

### Frontend
- **Framework**: React 18
- **Build Tool**: Vite
- **HTTP Client**: Axios
- **Styling**: Vanilla CSS with modern design patterns

## Project Structure

```
url-shortner/
├── backend/
│   ├── src/main/java/com/urlshortener/
│   │   ├── domain/          # Entity classes
│   │   ├── repository/      # Spring Data JPA repositories
│   │   ├── service/         # Business logic
│   │   ├── controller/      # REST API controllers
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── exception/       # Exception handling
│   │   └── config/          # Configuration classes
│   ├── src/main/resources/
│   │   └── application.yml  # Application configuration
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/      # React components
│   │   ├── services/        # API service layer
│   │   ├── App.jsx          # Main application component
│   │   ├── main.jsx         # Entry point
│   │   └── index.css        # Global styles
│   ├── index.html
│   ├── vite.config.js
│   └── package.json
└── README.md
```

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Node.js 18+** and npm

### Backend Setup

1. Navigate to the backend directory:
   ```cmd
   cd backend
   ```

2. Build the project:
   ```cmd
   mvn clean package
   ```

3. Run the Spring Boot application:
   ```cmd
   mvn spring-boot:run
   ```

   The backend will start on **http://localhost:12000**
 
    - Health Check: http://localhost:12000/actuator/health
    - Swagger UI: http://localhost:12000/swagger-ui/index.html

### Frontend Setup

1. Navigate to the frontend directory:
   ```cmd
   cd frontend
   ```

2. Install dependencies:
   ```cmd
   npm install
   ```

3. Start the development server:
   ```cmd
   npm run dev
   ```

   The frontend will start on **http://localhost:5173**

## Running with Docker

The easiest way to run the entire stack is using Docker Compose.

### Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop) installed and running
- [Docker Compose](https://docs.docker.com/compose/install/)

### Steps

1. **Build and start the containers**:
   ```cmd
   docker-compose up --build
   ```

2. **Access the application**:
    - Frontend: http://localhost:3000
    - Backend API: http://localhost:12000
    - Swagger UI: http://localhost:12000/swagger-ui/index.html

3. **Stop the containers**:
   ```cmd
   docker-compose down
   ```

### Docker Services
- **Backend**: Spring Boot 17 image, builds using multi-stage Dockerfile.
- **Frontend**: Nginx image serving React production build, with API proxy configuration.

## API Endpoints

### Create Short URL
```http
POST /api/urls
Content-Type: application/json

{
  "url": "https://example.com/very-long-url",
  "expiryDays": 30
}
```

### Get All URLs
```http
GET /api/urls
```

### Get URL Details
```http
GET /api/urls/{shortCode}
```

### Delete URL
```http
DELETE /api/urls/{shortCode}
```

### Redirect (Main Feature)
```http
GET /{shortCode}
```
Returns: HTTP 302 redirect to the original URL

## Usage

1. **Start both servers** (backend on :12000, frontend on :5173 for dev or :3000 for Docker)
 
2. **Open your browser** to http://localhost:5173 (Dev) or http://localhost:3000 (Docker)

3. **Create a short URL**:
    - Enter a long URL (must start with http:// or https://)
    - Optionally set expiry days
    - Click "Shorten URL"

4. **Copy and share** the generated short URL

5. **View your URLs** in the list below, showing:
    - Short code
    - Original URL
    - Click count
    - Creation date
    - Expiry date (if set)

6. **Test the redirect** by opening the short URL in a new tab

## Configuration

### CORS

CORS is configured to allow requests from `http://localhost:5173` and `http://localhost:3000`. Update `application.yml` to add more origins:

```yaml
cors:
  allowed-origins: http://localhost:5173,http://your-domain.com
```

## Features Explained

### Short Code Generation
- Uses **Base62 encoding** (0-9, A-Z, a-z)
- Generates **6-character codes** (56 billion+ unique combinations)
- **Collision detection** with retry mechanism
- **Cryptographically secure** random generation

### Click Tracking
- Automatically increments click count on each redirect
- Tracks total clicks per shortened URL
- Displayed in the URL list

### Expiry Handling
- Optional expiry dates for time-limited links
- Automatic validation on access
- Returns HTTP 410 (Gone) for expired URLs

### Error Handling
- Comprehensive exception handling
- User-friendly error messages
- Validation for URL format and input

## Building for Production

### Backend
```cmd
cd backend
mvn clean package
java -jar target/url_shortener-1.0.0.jar
```

### Frontend
```cmd
cd frontend
npm run build
```


**Made with ❤️ using Spring Boot, React, and Java 17**
