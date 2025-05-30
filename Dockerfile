FROM eclipse-temurin:21-jre

# Argumentos que puedes pasar en docker build
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
