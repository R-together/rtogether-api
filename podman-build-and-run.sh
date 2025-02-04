#!/bin/bash

echo "Exporting env variables..."
export $(cat .env | xargs)

echo "Building the Spring Boot application..."
mvn clean package test

echo "Building the Docker image..."
podman-compose build

echo "Running the Docker container..."
podman-compose up -d

echo "Application is now running at http://localhost:8082"