

FROM openjdk:11
ADD ./target/muzixApp-0.0.1-SNAPSHOT.jar /usr/src/muzixApp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR usr/src
ENTRYPOINT ["java","-jar","muzixApp-0.0.1-SNAPSHOT.jar"]
