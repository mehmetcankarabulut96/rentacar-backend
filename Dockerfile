# -------- step 1: build --------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src

# Build
RUN mvn clean package -DskipTests


# -------- step 2: run --------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 7777
ENTRYPOINT ["java", "-jar", "app.jar"]