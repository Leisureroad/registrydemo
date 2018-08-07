FROM openjdk:latest
MAINTAINER Fan Liu <t-fan.liu@pcitc.com>
ADD target/registrydemo-0.0.1-SNAPSHOT.jar /app/
#CMD ["java", "-Xmx200m", "-jar", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=7085,suspend=n", "/app/appcenter-service.jar"]
CMD ["java", "-jar", "/app/appcenter-service.jar"]