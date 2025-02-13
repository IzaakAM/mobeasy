FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml /build/pom.xml
RUN mvn dependency:go-offline

COPY src /build/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app

COPY --from=build /build/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
