FROM openjdk:11-jdk-slim AS final
WORKDIR /data
COPY ./build/libs/severstal-0.0.1-SNAPSHOT.jar /data/app.jar
ENTRYPOINT ["/bin/sh", "-c", "java -jar -Dserver.port=7777 app.jar"]