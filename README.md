# Vehicle Store REST API

This is a very simple REST API which allows you to store and retrieve vehicles
in a PostgreSQL database. The application is built with Kotlin and Spring Boot.

### 1. Installation
   `git clone https://github.com/Gubancs/vehicle-store`
### 2. Build the application
   `./gradlew build`
### 3. Start the application
   `./gradlew bootRun`
### 4. Build the Docker image
   `docker build -t vehicle-store .`
### 5. Run the application with Docker Compose
   `docker-compose down && docker-compose up --build`

## Endpoints 
- POST /jarmuvek store a new vehicle in the database
- GET /jarmuvek/{uuid} find vehicle by uuid
- GET /kereses search for vehicles by plate number, owner and attributes
- GET /jarmuvek : count all vehicles stored in the database

### Technologies, frameworks and libraries used
- Kotlin 1.9
- Java 21
- Spring Boot 3x
- Spring Data JPA
- Spring Web
- Spring Cache
- Spring Undertow
- Hibernate 6x
- PostgreSQL
- Docker
