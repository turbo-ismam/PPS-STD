# syntax=docker/dockerfile:1

FROM gradle:7.3-jdk17-alpine as base
ENV APP_HOME=/ScalaTowerDefense
WORKDIR $APP_HOME
COPY --chown=gradle:gradle gradlew gradlew.bat settings.gradle.kts ./
COPY --chown=gradle:gradle gradle ./gradle
COPY --chown=gradle:gradle app ./app
RUN gradle --version

FROM base as test
WORKDIR $APP_HOME
CMD ["gradle", "test"]