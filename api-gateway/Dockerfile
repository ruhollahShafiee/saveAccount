FROM maven:3.8-openjdk-8@sha256:1d4f75adc85edeb0f5748eebe2cecacd4f9e7ee8ad324c82e24d30c4f65c8b57 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn package -e

FROM openjdk:8u312-jre@sha256:be64c29adbd1fdf44db51af8bda44481ef4898ab89b28ff9eb966d06a7241c0b AS production
EXPOSE 8760
RUN wget https://github.com/Yelp/dumb-init/releases/download/v1.2.5/dumb-init_1.2.5_amd64.deb
RUN dpkg -i dumb-init_*.deb
RUN mkdir /app
COPY --from=build /project/target/api-gateway.jar /app/api-gateway.jar
WORKDIR /app
CMD "dumb-init" "java" "-jar" "api-gateway.jar"