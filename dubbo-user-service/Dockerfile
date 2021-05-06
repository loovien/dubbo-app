FROM openjdk:8u292-jre

WORKDIR /dubbo-user-service

ADD target/dubbo-user-service-*-repackage.jar ./dubbo-user-service.jar

EXPOSE 80

CMD ["java", "-jar", "dubbo-user-service.jar"]