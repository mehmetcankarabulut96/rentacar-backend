# Rent A Car REST API

Car rental backend system using Spring Boot with traditional N-Layered Architecture.

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL (Local)

## Architecture & Design
- Monolith N-Layered Architecture
- Request–Response pattern
- Global exception handling
- Custom mapper implementation
- Docker support

## Current Status
- Completed Services
- Completed unit tests;
  - ModelManager ( 73% lines covered )
  - CarBusinessRules ( 100% lines covered )
- Creating dev-pipeline.yaml file

## Future Improvements
- CI/CD pipeline

## 🚀 Application Setup Guide

### 🐳 Running the Application with Docker
If you want to run the application using Docker containers, follow the steps below.

### 1. Clone the repository
```bash
git clone https://github.com/mehmetcankarabulut96/rentacar-backend <repository_name>
cd <repository-name>
```

### 2. Build the application Docker image
```bash
docker build -t app_image_name:app_image_tag .
```

### 3. Create a .env file
Create a .env file in the project root and add the following environment variables.
#### PostgreSQL configuration
```bash
POSTGRES_DB=your_db_name
POSTGRES_USER=your_username
POSTGRES_PASSWORD=your_password
```
#### Spring Boot application configuration
```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://db_container_name:5432/${POSTGRES_DB}
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
```
#### Environment variable rules
- POSTGRES_USER must be the same as SPRING_DATASOURCE_USERNAME
- POSTGRES_PASSWORD must be the same as SPRING_DATASOURCE_PASSWORD
- SPRING_DATASOURCE_URL must follow this format:
```bash
jdbc:postgresql://db_container_name:5432/${POSTGRES_DB}
```

### 4. Create a Docker network
Both containers will communicate over this network.
```bash
docker network create network_name
```

### 5. Start the PostgreSQL container
PostgreSQL must be started before the application container.
The -v option is optional.
If a volume name is provided, database data will persist even if the container is removed.
```bash
docker run -d --name db_container_name --network network_name --env-file .env.dev -v volume_name:/var/lib/postgresql/data postgres:15
```

### 6. Start the application container
```
docker run -d --name app_container_name --network network_name --env-file .env -p app_port_address:7777 app_image_name:app_image_tag
```

### 7. Test the application
Use Postman or any HTTP client and send a request to:
```bash
http://localhost:app_port_address/api/brands
```
If the response status is 200 OK, the application is running successfully ✅

### 💻 Running the Application with an IDE (Without Docker)
Update the datasource configuration according to your local PostgreSQL setup.

### 1. Install PostgreSQL locally
- Download and install PostgreSQL from: https://www.postgresql.org/download/
- After installation, manually create the database.

### 2. Update application.yaml
Update the datasource configuration according to your local PostgreSQL setup.
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/${DB_NAME}
    username: ${POSTGRES_USER}
    password: ${POSTGRES_PASSWORD}
```
You can define these variables either:
- in a .env file, or
- directly inside application.yaml

### 3. Run and test the application
Start the application and send a request to:
```bash
http://localhost:7777/api/brands
```
If the response status is 200 OK, the setup is successful 🎉