FROM openjdk:8-jdk-alpine
RUN mkdir -p /home/tradesoft
COPY ./target/exchanges-services-0.0.1-SNAPSHOT.jar /home/tradesoft/tradesoft.jar
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar","/home/tradesoft/tradesoft.jar"]