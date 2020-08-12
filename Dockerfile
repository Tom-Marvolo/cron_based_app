FROM gradle:jdk11-slim AS builder
WORKDIR /usr/src/
USER root
COPY . crontest/
COPY build.gradle .
COPY settings.gradle .
RUN gradle build -x test

FROM openjdk:11-jre
USER root
WORKDIR /usr/src
COPY crontest/docker-startup.sh .
RUN chmod +x *.sh
COPY --from=builder /usr/src/crontest/build/libs/crontest-0.0.1-SNAPSHOT.jar .
CMD ["/bin/sh", "docker-startup.sh"]


