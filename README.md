# Cargo Compass App

## Overview

TransMarket is an innovative platform transforming the freight transportation industry. It facilitates efficient freight finding, cargo transport, and logistics management.

## Features and Tech Stack

- **Microservices Architecture**: Each service (User, Cargo, Company, Offer, Vehicle) functions independently, communicating via APIs, ensuring scalability and robustness.
  
- **Security and Authentication**: Leveraging Spring Security and JWT for secure access and authentication.

- **Data Management**: Isolated PostgreSQL databases for each microservice ensure data integrity.

- **API Gateway**: Centralized Spring Cloud Gateway routes requests, serving as a unified entry point.

- **Service Registry**: Eureka Server for seamless service discovery and communication.

- **Dockerization**: Docker and Docker Compose facilitate deployment and management of services and databases.

- **Front-end Development**: Upcoming React-based interface focusing on responsiveness and user experience.

## Future Enhancements

- **Mailing Service**: Integration for automated email notifications to enhance communication.

- **Continuous Development**: Ongoing project updates to introduce new features and improvements.

## Performance Monitoring

- **Prometheus, Grafana, and Loki**: Integrated for comprehensive performance monitoring and log management, providing real-time insights into system performance and operational health.

## Running the Application

### With Docker

1. Navigate to the project directory:

    ```
    cd CargoCompass
    ```

2. Build the Docker images:

    ```
    mvn clean package -P build-docker-images
    ```

3. Start the application:

    ```
    docker-compose up -d
    ```

### Locally

1. Navigate to the project directory:

    ```
    cd CargoCompass
    ```

2. Run the application:

    ```
    mvn spring-boot:run
    ```

## Contributors
- [Cihan Dilsiz](https://github.com/cdilsiz5)

