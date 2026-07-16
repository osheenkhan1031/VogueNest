# Build stage: Yahan Java 21 use karenge
FROM eclipse-temurin:21-jdk-alpine AS build
COPY . .
RUN ./mvnw clean package -DskipTests

# Run stage: Yahan sirf final JAR chalayenge
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]