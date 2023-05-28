FROM openjdk:17-jdk
COPY  /target/*.jar /usr/local/lib/app.jar
EXPOSE 7005
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

