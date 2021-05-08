FROM openjdk:8u292-jre

WORKDIR /dubbo-user-service

ADD target/dubbo-user-service-*-repackage.jar ./dubbo-netty-gateway.jar

EXPOSE 80

CMD ["java", "-jar", "dubbo-netty-gateway.jar"]