#!/bin/bash

if ! docker info > /dev/null 2>&1; then
    echo "Docker daemon is not running. Please start Docker and try again."
    exit 1
fi

if ! ./employee-service/mvnw -v > /dev/null 2>&1; then
  echo "Maven wrapper not found."
  exit 1
fi

echo "Starting MySQL with Docker.... "
docker compose -f database/docker-compose.yml up -d

sleep 5

echo "Starting Spring Boot application.... "
cd employee-service/
./mvnw spring-boot:run
