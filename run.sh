#!/bin/bash

echo "Starting MySQL with Docker.... "
docker compose -f database/docker-compose.yml up -d

sleep 3

echo "Starting Spring Boot application.... "
cd employee-service/
./mvnw spring-boot:run