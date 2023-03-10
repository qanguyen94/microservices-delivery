FROM eclipse-temurin:11

WORKDIR /my-app

COPY ./target/delivery-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "delivery-0.0.1-SNAPSHOT.jar"]
