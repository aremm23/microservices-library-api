# Spring Cloud Microservices Project

This project is a microservices architecture using Spring Cloud, PostgreSQL, and RabbitMQ. The services included are:

- **Eureka Service** (Service Discovery)
- **Config Service** (Centralized Configuration)
- **Gateway Service** (API Gateway)
- **Authentication Service** (Authentication and Authorization)
- **Core Service** (Business Logic)
- **Library Service** (Specific domain logic)

## Prerequisites

- **Docker** installed and running
- **Java 17** or higher installed
- **Maven** installed

## Setup and Start the Services

# Step 1: Start PostgreSQL and RabbitMQ

The project relies on PostgreSQL and RabbitMQ, which can be easily started using Docker Compose.

Ensure you are in the project root directory.

Run the following command to start PostgreSQL and RabbitMQ containers:

`docker-compose up -d`

This will start the PostgreSQL database on its default port (5432) and RabbitMQ on its default port (5672).

# Step 2: Start the Eureka and Config Services

It is essential to start Eureka (service discovery) and Config (configuration management) services before other
services.
1. Start the Eureka Service:

```
docker-compose up --build eureka
```
    
2. After the Eureka Service is up and running, start the Config Service:

```
docker-compose up --build config
```

# Step 3: Start the Remaining Services

Once Eureka and Config services are running, you can start the remaining services. You can run them in any order, as
long as Eureka and Config are already up.

1. Gateway Service:

```
docker-compose up --build gateway
```

2. Authentication Service:

```
docker-compose up --build auth
```

3. Core Service:

```
docker-compose up --build core
```

4. Library service

```
docker-compose up --build library
```

# Step 4: Verify the Services

Eureka Dashboard can be accessed at http://localhost:8761. You should see all the registered services here.
Gateway Service can be accessed at http://localhost:8080. This acts as the API gateway, routing requests to appropriate
services.
Each service is registered with Eureka, and service-to-service communication is handled via the gateway.

# Additional Information

Configuration: Make sure your Config Service is correctly configured to pull configurations from your repository.
Docker Compose: PostgreSQL and RabbitMQ should always be up and running before starting the services.
Service Registration: All services are automatically registered with Eureka. For seamless interaction, ensure that
Eureka and Config services are running before starting other services.