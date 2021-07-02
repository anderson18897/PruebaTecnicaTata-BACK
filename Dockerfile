FROM openjdk:8
VOLUME /tmp
EXPOSE 8095
ADD ./target/docker-h2-1.jar app-tata.jar
ENTRYPOINT ["java","-jar","/app-tata.jar"]