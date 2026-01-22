# Rent A Car REST API

Spring Boot REST API project developed using traditional N-Layered Architecture.

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
- Refactoring services & business rules

## Future Improvements
- Refactor services and business rules that call directly other repositories or business rules
- Validation of request dto's
- Refactor response dto's (add child objects to dto)
- Mapper optimizations
- Switch Page to PagedModel
- Unit tests
- Swagger / OpenAPI documentation
- Docker support
- CI/CD pipeline

## Configuration
- `application.yaml` is included as a template.
- Local configuration is provided via `application-local.yaml` using Spring Profiles.
