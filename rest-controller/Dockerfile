FROM maven:3.8-openjdk-8@sha256:1d4f75adc85edeb0f5748eebe2cecacd4f9e7ee8ad324c82e24d30c4f65c8b57 AS build
#RUN mkdir /project
WORKDIR /usr/local/src/project
COPY ./saveAccount ./
RUN ls -la && mvn -Dmaven.repo.local=.m2/repository package -DskipTests -e

FROM openjdk:8u312-jre@sha256:be64c29adbd1fdf44db51af8bda44481ef4898ab89b28ff9eb966d06a7241c0b AS production
RUN mkdir /app
WORKDIR /app
COPY --from=build /usr/local/src/project/rest-controller/target/save-account-exec.jar /app/save-account-exec.jar
CMD ["java", "-jar", "save-account-exec.jar", "--p", "9100"]
