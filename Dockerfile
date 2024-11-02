FROM eclipse-temurin:21

ARG JAR_FILE=./target/*.jar

COPY ${JAR_FILE} ./opt/userapi.jar

ENTRYPOINT ["java", "-jar", "/opt/userapi.jar"]