#eval "../../mvnw -B --file ../../pom.xml -s ../../settings.xml  -DskipTests=true clean install"
docker-compose -f ../docker/docker-compose-dev.yaml -p microservices up -d --build
