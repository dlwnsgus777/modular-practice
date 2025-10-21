# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **modular monolith** practice project built with Kotlin, Spring Boot, and JPA. The application implements a product management system with member authentication and wishlist functionality.

## Architecture

### Module Structure

The project follows a **domain-based modular architecture** where each domain is divided into three layers:

```
{domain}/
├── {domain}-api/       # Controllers, DTOs, Executors (presentation layer)
├── {domain}-domain/    # Services, Business logic (domain layer)
└── {domain}-infra/     # Repositories, External integrations (infrastructure layer)
```

**Domains:**
- **member**: User registration and authentication
- **product**: Product CRUD operations with validation (profanity filtering, special character rules)
- **wishlist**: User-specific product wishlist management
- **auth**: JWT-based authentication and security configuration
- **common**: Shared utilities, annotations, entities, exceptions, and configuration
- **app**: Main application module that aggregates all domain modules

**Dependency Rules:**
- `api` depends on `domain` (and optionally `common`, `auth`)
- `domain` depends only on `common`
- `infra` depends on `domain` (and optionally `common`)
- `app` module depends on all other modules and contains the main application entry point

### Layer Pattern

The codebase follows a **Command/Query separation** pattern:
- **command**: Write operations (create, update, delete)
- **query**: Read operations (queries, lookups)

Within each operation type:
1. **Controller** (in `*-api`) receives HTTP requests
2. **Executor** (in `*-api`) orchestrates the operation
3. **Service** (in `*-domain`) contains business logic
4. **Repository** (in `*-infra`) handles data persistence

## Build and Test Commands

### Build the project
```bash
./gradlew build
```

### Run all tests
```bash
./gradlew test
```

### Run tests for a specific module
```bash
./gradlew :app:test
./gradlew :member:member-domain:test
./gradlew :product:product-api:test
```

### Run a single test class
```bash
./gradlew :app:test --tests "com.modular.product.command.api.ProductSaveControllerTest"
```

### Run a single test method
```bash
./gradlew :app:test --tests "com.modular.product.command.api.ProductSaveControllerTest.testMethodName"
```

### Run the application
```bash
./gradlew :app:bootRun
```

### Lint with ktlint
```bash
./gradlew ktlintCheck
```

### Format code with ktlint
```bash
./gradlew ktlintFormat
```

## Testing Strategy

- All **E2E tests** are located in the `app` module under `app/src/test/`
- Tests follow the **AAA (Arrange-Act-Assert)** pattern with clear comment sections
- Unit tests use **MockK** for mocking Kotlin classes
- Integration tests use `@SpringBootTest` with the full application context
- Test file naming: `*Test.kt` for all test classes

## Key Technical Details

### Authentication
- Uses **JWT tokens** for authentication
- Token-based access control for wishlist endpoints
- Custom `@MemberId` annotation extracts member ID from JWT in controllers
- Security configuration in the `auth` module

### Product Validation Rules
- Product names: max 15 characters (including spaces)
- Allowed special characters: `()`, `[]`, `+`, `-`, `&`, `/`, `_`
- Profanity filtering via **PurgoMalum** external API integration (in `product-infra`)

### Database
- H2 in-memory database for development and testing
- JPA entities use Kotlin `allOpen` plugin for `@Entity`, `@Embeddable`, `@MappedSuperclass`

### Gradle Configuration
- All submodules have `bootJar { enabled = false }` except `app` module
- Only the `app` module produces an executable JAR
- Common dependencies (Spring Boot, JPA, Security, JWT, etc.) configured in root `build.gradle.kts`

## Development Notes

- **Module isolation**: Each domain module should remain independent; avoid cross-domain dependencies except through the `app` module
- **Executor pattern**: Business operations are orchestrated in Executor classes (in `*-api` modules), which call Services (in `*-domain` modules)
- **DTOs**: Request/Response DTOs are defined in `*-api` modules alongside their controllers
- **Service layer**: Contains only business logic, no HTTP concerns
- When adding new functionality, follow the existing command/query separation and layered structure

## Code Design Principles

### SOLID Principles
When implementing code, strictly adhere to SOLID principles:

- **Single Responsibility Principle (SRP)**: Each class should have only one reason to change. Keep classes focused on a single responsibility
- **Open/Closed Principle (OCP)**: Classes should be open for extension but closed for modification. Use interfaces and abstractions to allow behavior extension
- **Liskov Substitution Principle (LSP)**: Derived classes must be substitutable for their base classes without altering program correctness
- **Interface Segregation Principle (ISP)**: Clients should not depend on interfaces they don't use. Create specific, focused interfaces rather than large general-purpose ones
- **Dependency Inversion Principle (DIP)**: Depend on abstractions, not concretions. High-level modules should not depend on low-level modules; both should depend on abstractions

### Clean Architecture Guidelines

- **Dependency Rule**: Dependencies must point inward toward the domain. The domain layer should not depend on outer layers (api, infra)
- **Entity Independence**: Domain entities and business logic should be independent of frameworks, UI, databases, and external agencies
- **Testability**: Business rules should be testable without the UI, database, web server, or any external element
- **Domain-Driven Design**: Use clear domain language in code. Business concepts should be reflected in class and method names
- **Separation of Concerns**: Keep different aspects of the application (presentation, business logic, data access) properly separated across layers

## Feedback Request Protocol

**After completing any implementation, always request feedback from the user before proceeding.**

When requesting feedback:
- Clearly state what has been completed (e.g., implementation, tests, refactoring)
- **Specify areas that require particular attention** (e.g., "I'd especially appreciate feedback on the abstraction design and whether the dependency injection follows clean architecture principles")
- Wait for explicit user approval before moving to the next stage
- Examples of focus areas to mention:
  - Architecture and design decisions
  - SOLID principles adherence
  - Test coverage and quality
  - Error handling approach
  - Performance considerations
  - Code readability and maintainability

## Test Method Structure (AAA Pattern)
- **Use lowercase comments**: `// arrange`, `// act`, `// assert`
- **Arrange**: Set up test data, mocks, and preconditions
- **Act**: Execute the code under test
- **Assert**: Verify the expected outcomes


## Test Data Management
- **Use `@Transactional`**: Ensure test isolation with automatic rollback
- **Helper methods**: Create private methods for test data setup
- **Naming**: Use descriptive names like `createValidOutletAccount()`, `createBlockedOutletAccount()`
- **EntityManager usage**: Use direct SQL for complex test data setup when needed

## Summary instructions
- When you are using compact, please focus on test output and code changes