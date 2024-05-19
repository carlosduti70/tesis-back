FROM openjdk:17-jdk-alpine
ADD target/alzheimer-s-project-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
# cambiar appn por security