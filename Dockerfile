FROM eclipse-temurin:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar /app/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/porjectoFinalPostgre-0.0.1-SNAPSHOT.jar"]
#ejecutar comando build: docker build -t proyectodocker .
#Para ejecutar iamgen una vez build : docker run -d -p 8080:8080 proyectodocker