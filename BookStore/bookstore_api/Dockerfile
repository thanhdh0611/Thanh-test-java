# ===== Build stage =====
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src

# Copy pom trước để tận dụng cache
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline || true

# Copy source và build
COPY . .
RUN mvn -q -DskipTests clean package

# ===== Runtime stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar (lấy jar chính, tránh nhầm sources/javadoc)
COPY --from=build /src/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
