FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /opt/userapi

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /opt/userapi

COPY --from=build /opt/userapi/target/*.jar userapi.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "userapi.jar"]