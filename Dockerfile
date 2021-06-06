FROM openjdk:16
ADD build/libs/VatRateApp-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]