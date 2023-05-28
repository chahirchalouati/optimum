

FROM openjdk:17-jdk
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7003
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

