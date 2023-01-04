#!/usr/bin/env sh
#eval "../../mvnw -B --file ../../pom.xml -s ../../settings.xml  -DskipTests=true clean install"
docker-compose -f ../docker/docker-compose-dev.yaml -f ../docker/docker-compose-services.yaml -p microservices up -d
