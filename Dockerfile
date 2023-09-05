FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=Songlist-1.0-SNAPSHOT.jar
COPY target/${JAR_FILE} /app.jar
CMD ["java", "-jar", "/app.jar"]
