#FROM openjdk:12-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar /app/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar"]