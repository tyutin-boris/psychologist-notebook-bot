FROM amazoncorretto:17.0.9-alpine

WORKDIR ./app

COPY ./target/psychologist-notebook-bot-0.1.1-SNAPSHOT.jar ./app.jar

EXPOSE 9010

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
