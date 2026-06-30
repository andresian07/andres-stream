# Etapa 1: build
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src
RUN chmod +x gradlew
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: imagen final, mas liviana
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Render inyecta la variable PORT; tu app la usa via server.port en application-prod.properties
EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
