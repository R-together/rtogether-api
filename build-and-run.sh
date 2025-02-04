#!/bin/bash

echo "Exporting env variables..."
export $(cat .env | xargs)

echo "Building the Spring Boot application..."
mvn clean package test

echo "Building the Docker image..."
docker-compose build

echo "Running the Docker container..."
docker-compose up -d

echo "Application is now running at http://localhost:8082"