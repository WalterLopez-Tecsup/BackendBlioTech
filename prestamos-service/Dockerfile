FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q clean package -DskipTests

FROM amazoncorretto:21-al2023-headless
WORKDIR /app
RUN groupadd -r app && useradd -r -g app app
COPY --from=build --chown=app:app /app/target/*.jar app.jar
USER app
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]