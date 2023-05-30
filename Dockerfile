FROM eclipse-temurin:17
EXPOSE ${PORT}
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv6Addresses=false"]
#ejecutar comando build: docker build -t proyectodocker .
#Para ejecutar iamgen una vez build : docker run -d -p 8080:8080 proyectodocker
#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#COPY target/*.jar app.jar
#EXPOSE ${PORT}
#ENTRYPOINT ["java","-jar","/app.jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv6Addresses=false"]