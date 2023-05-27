FROM openjdk:17-jdk-slim
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7005
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

