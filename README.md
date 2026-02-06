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
- DTO-based communication

## Current Status
- All CRUD operations completed
- Completed unit tests;
  - ModelManager ( 73% lines covered )
  - CarBusinessRules ( 100% lines covered )

## Future Improvements
- Swagger / OpenAPI documentation
- Docker support
- CI/CD pipeline

## Configuration
- `application.yaml` is included as a template.
- Local configuration is provided via `application-local.yaml` using Spring Profiles.