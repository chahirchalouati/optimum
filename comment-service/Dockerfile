FROM eclipse-temurin:17-alpine
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7005
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

