FROM openjdk:20-ea-1-jdk-slim
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7009
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

