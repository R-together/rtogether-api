#!/bin/bash

echo "Exporting env variables..."
export $(cat .env | xargs)

echo "Building the Spring Boot application..."
mvn clean package -DskipTests

echo "Building the Docker image..."
docker-compose build

echo "Running the Docker container..."
docker-compose up -d

echo "Executing unit tests..."
mvn clean test

echo "Application is now running at http://localhost:8082"