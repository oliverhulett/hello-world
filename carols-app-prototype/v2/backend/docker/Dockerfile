ARG BASE_IMAGE_TAG=19.10
ARG BASE_IMAGE=ubuntu
FROM $BASE_IMAGE:$BASE_IMAGE_TAG

ARG VERSION

RUN apt-get update -y && apt-get install -y \
	dumb-init \
	openjdk-13-jre \
	wget \
;

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
	&& tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
	&& rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

EXPOSE 8080
EXPOSE 9010

WORKDIR /opt/service/run/

RUN mkdir -p /opt
COPY docker /opt/service/
RUN mkdir -p /opt/service/lib/ /opt/service/run/
COPY main/build/libs/application-$VERSION.jar /opt/service/lib/service.jar

ENTRYPOINT [ "/usr/bin/dumb-init", "--" ]
CMD [ "dockerize", "-wait", "tcp://mongodb:27017", "/opt/service/bin/entrypoint.sh" ]
