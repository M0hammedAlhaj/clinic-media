# Clinic Media

A comprehensive Spring Boot application for managing clinic operations, facilitating relationships between healthcare providers, appointment scheduling, and user management.

## Overview

Clinic Media is a REST API-based healthcare management system that serves multiple user types including administrators, clinics, doctors, and patients. The system provides comprehensive security, booking management, and user relationship capabilities.

## Technology Stack

- **Java**: 21
- **Spring Boot**: 3.4.5
- **Database**: PostgreSQL
- **Security**: Spring Security + JWT
- **ORM**: Spring Data JPA + Hibernate
- **Build Tool**: Maven
- **Documentation**: SpringDoc OpenAPI

## Key Features

- **Multi-role Authentication**: Admin, Clinic, Doctor, Patient, and Lab user types
- **JWT-based Security**: Stateless authentication with role-based authorization
- **Request Management**: Doctor-Clinic relationship handling
- **Booking System**: Appointment scheduling with conflict detection
- **Insurance Management**: Patient and clinic insurance operations
- **User Management**: Registration, activation, and profile management

## Architecture

The application follows a layered architecture with domain-driven design principles:

- **Presentation Layer**: REST controllers for different user types
- **Security Layer**: JWT authentication and role-based authorization
- **Application Services**: Business logic for requests, bookings, and user management
- **Domain Layer**: Core entities and business rules
- **Infrastructure Layer**: Repository adapters and data persistence

## Quick Start

### Prerequisites

- Java 21
- PostgreSQL database
- Maven 3.11.0+

### Database Setup

1. Create a PostgreSQL database
2. Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.properties.hibernate.default_schema=clinic
   ```

### Running the Application

```bash
# Clone the repository
git clone <repository-url>

# Navigate to the project directory
cd clinic-media/backend/clinic-media

# Run with Maven
./mvnw spring-boot:run
```

The application will start on the default port with automatic schema creation enabled.

## API Documentation

Once the application is running, access the interactive API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## User Types and Access Levels

- **Admin** (`ROLE_ADMIN`): Full system access via `/api/v1/admin/**`
- **Clinic** (`ROLE_CLINIC`): Clinic operations via `/api/v1/clinics/**`
- **Doctor** (`ROLE_DOCTOR`): Doctor operations via `/api/v1/doctors/**`
- **Patient**: Public and authenticated endpoints
- **Lab**: Extended user type for laboratory operations

## Core Business Domains

1. **User Management**: Registration, activation, and profile management
2. **Request Management**: Doctor-Clinic relationship requests
3. **Booking Management**: Appointment scheduling with availability checking
4. **Insurance Management**: Insurance operations for patients and clinics

## Development

### Project Structure

backend/clinic-media/
├── src/main/java/com/spring/clinicmedia/
│   ├── ClinicMediaApplication.java          # Main application class
│   ├── domain/                              # Domain entities and ports
│   ├── infrastructure/                      # Infrastructure implementations
│   └── presentation/                        # REST controllers and config
├── src/main/resources/
│   └── application.properties               # Configuration
└── pom.xml                                  # Maven dependencies

### Key Dependencies

- Spring Boot Starter Web, Data JPA, Security, Validation
- PostgreSQL Driver
- JWT (io.jsonwebtoken)
- MapStruct for entity-DTO mapping
- Lombok for boilerplate reduction
- SpringDoc OpenAPI for documentation

## Security

The application implements JWT-based authentication with:
- BCrypt password encryption
- Role-based access control
- Custom JWT filter for token validation
- Stateless session management

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/M0hammedAlhaj/clinic-media)


