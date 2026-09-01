FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN MAVEN_OPTS="-Xmx384m" mvn package -pl api-gateway -DskipTests -B

FROM amazoncorretto:21-al2023-headless
WORKDIR /app
COPY --from=build /app/api-gateway/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]