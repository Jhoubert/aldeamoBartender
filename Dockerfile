FROM openjdk:8-jdk-alpine as builder
WORKDIR /workspace/app/
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw package -Dhttps.protocols=TLSv1.2 -DskipTests

FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir /app
COPY --from=builder /workspace/app/target/Bartender1-0-PROTOTYPE.jar /app/Bartender1-0-PROTOTYPE.jar
ENTRYPOINT ["java","-jar","/app/Bartender1-0-PROTOTYPE.jar"]