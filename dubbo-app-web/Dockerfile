FROM openjdk:8u292-jre

WORKDIR /dubbo-user-service

ADD target/dubbo-web-app-*-repackage.jar ./dubbo-web-app.jar

EXPOSE 80

CMD ["java", "-jar", "dubbo-web-app.jar"]