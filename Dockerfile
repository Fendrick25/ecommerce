FROM openjdk:17-jdk
WORKDIR /app

COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8380
ENTRYPOINT ["java","-jar","app.jar"]