FROM openjdk:11
COPY ./target/pen-ws-pensjonsinformasjon-v1-fnr-mockserver-1.0-SNAPSHOT.jar /app/app.jar
WORKDIR /tmp
CMD ["java","-jar","/app/app.jar"]
