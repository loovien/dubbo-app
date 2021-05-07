FROM openjdk:8u292-jre

WORKDIR /dubbo-user-service

ADD target/dubbo-message-service-*-repackage.jar ./dubbo-message-service.jar

EXPOSE 80

CMD ["java", "-jar", "dubbo-message-service.jar"]