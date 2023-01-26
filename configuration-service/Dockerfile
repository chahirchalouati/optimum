

FROM eclipse-temurin:17-alpine
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

