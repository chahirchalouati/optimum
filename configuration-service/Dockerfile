FROM maven:3-eclipse-temurin-17-alpine AS build
COPY src /src
COPY pom.xml /
COPY settings.xml /
ARG USER_NAME=username
ARG PASSWORD=password
ARG ID=id
RUN --mount=type=cache,target=/root/.m2 mvn -f  /pom.xml  \
    -s /settings.xml  \
    -Drepo.id=${ID}  \
    -Drepo.login=${USER_NAME}  \
    -Drepo.pwd=${PASSWORD}  \
    clean install

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar /usr/local/lib/app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

