FROM artifactory/base-jdk:latest

RUN mkdir -p /appdata/logs/pushnotification && \
    chmod -R 777 /appdata/logs/pushnotification/

WORKDIR /appdata/


RUN curl --insecure -o BKMPushNotification.jar  $ARTIFACTORY_URL

EXPOSE 8080 

ENTRYPOINT ["java","-jar","BKMPushNotification.jar"]