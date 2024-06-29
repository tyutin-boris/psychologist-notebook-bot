FROM amazoncorretto:17.0.9-alpine

#ENV BOT_NAME=TestNNLigaGitLabBot
#ENV BOT_TOKEN=6525258392:AAHjB2fEs8Fdi7JtSDom4acCHV-VCbbowF8

WORKDIR ./app

COPY ./target/psychologist-notebook-bot-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app.jar"]
