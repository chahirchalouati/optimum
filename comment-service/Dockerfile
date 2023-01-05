

FROM eclipse-temurin:17-alpine
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7001
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

