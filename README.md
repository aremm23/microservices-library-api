# Spring Cloud Microservices Project

This project is a microservices architecture using Spring Cloud, PostgreSQL, and RabbitMQ. The services included are:

- **Eureka Service** (Service Discovery)
- **Config Service** (Centralized Configuration)
- **Gateway Service** (API Gateway)
- **Keycloak Authentication Service** (Authentication and Authorization)
- **Core Service** (Business Logic)
- **Library Service** (Specific Domain Logic)

## Prerequisites

- **Docker** installed and running

## Setup and Start the Services

### Step 1: Start All Services

With the updated setup, you can now start all services with a single command. Ensure you are in the project root directory.

Run the following command to start all services:

```bash
docker-compose up --build
```

This command will build and start all containers, including PostgreSQL, RabbitMQ, and your microservices, integrated with Keycloak.

# Step 2: Verify the Services

Eureka Dashboard can be accessed at http://localhost:8761. You should see all the registered services here.
Gateway Service can be accessed at http://localhost:8080. This acts as the API gateway, routing requests to appropriate
services.
Each service is registered with Eureka, and service-to-service communication is handled via the gateway.

# Additional Information

Configuration: Make sure your Config Service is correctly configured to pull configurations from your repository.
Docker Compose: PostgreSQL and RabbitMQ should always be up and running before starting the services.
Service Registration: All services are automatically registered with Eureka. For seamless interaction, ensure that
Eureka and Config services are running before starting other services.