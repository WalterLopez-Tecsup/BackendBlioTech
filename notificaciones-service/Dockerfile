FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN MAVEN_OPTS="-Xmx384m" mvn package -pl notificaciones-service -DskipTests -B

FROM amazoncorretto:21-al2023-headless
WORKDIR /app
COPY --from=build /app/notificaciones-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
