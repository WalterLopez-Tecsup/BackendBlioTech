FROM amazoncorretto:21-al2023-headless
RUN yum install -y curl && yum clean all
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]