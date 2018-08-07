FROM maven:alpine
MAINTAINER Fan Liu
EXPOSE 8080
COPY . /opt/workspace/
WORKDIR /opt/workspace/
RUN mvn package
RUN ls -al
WORKDIR /
#ADD target/registrydemo-0.0.1-SNAPSHOT.jar /app/
#CMD ["java", "-Xmx200m", "-jar", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=7085,suspend=n", "/app/appcenter-service.jar"]
CMD ["java", "-jar", "/opt/workspace/target/registrydemo-0.0.1-SNAPSHOT.jar"]