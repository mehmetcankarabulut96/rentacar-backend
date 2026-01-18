# Rent A Car REST API

Spring Boot REST API application developed using traditional N-Layered Architecture.

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
- Brand & Model & Car & Rental CRUD operations completed
- Designing Customer entity and its relations

## Future Improvements
- Customer CRUD operations
- Refactor services and business rules that call directly other repositories or business rules
- Mapper optimizations
- Switch Page to PagedModel
- Swagger / OpenAPI documentation
- Docker support
- Unit tests
- CI/CD pipeline

## Configuration
- `application.yaml` is included as a template.
- Local configuration is provided via `application-local.yaml` using Spring Profiles.
