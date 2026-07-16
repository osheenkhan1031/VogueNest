# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build

# Work directory set karein
WORKDIR /app

# Permission set karein aur files copy karein
COPY . .
RUN chmod +x mvnw

# Build karein (skip tests taaki speed bani rahe)
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Build stage se sirf jar file copy karein (filename wildcard ke sath)
COPY --from=build /app/target/*.jar app.jar

# Application run karne ke liye entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]