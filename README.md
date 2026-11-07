# Skill Service

A standalone Spring Boot REST API for managing a skill catalog. It runs on localhost at port 8084 and stores its records in a local H2 database under data/. The API is intended for local development and has no authentication.

## Build and run

Use JDK 21 or later and Maven 3.6.3 or later.

    mvn clean package
    java -jar target/skill-service-0.0.1-SNAPSHOT.jar

## API

| Method | Path | Success |
| --- | --- | --- |
| POST | /api/skills | 201 Created |
| GET | /api/skills | 200 OK |
| GET | /api/skills/{id} | 200 OK |
| PUT | /api/skills/{id} | 200 OK |
| DELETE | /api/skills/{id} | 204 No Content |

POST and PUT accept JSON, for example:

    {"name":"Spring Boot","description":"Build and maintain Spring Boot applications."}

The name is required and the description is optional. PUT replaces both fields. Invalid requests return HTTP 400, and requests for unknown skill IDs return HTTP 404.

## Upload to GitHub

Extract the ZIP and upload its contents, including .gitignore, to the repository root.
