FROM openjdk:17-jdk-slim

# Install netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/*.jar app.jar
COPY wait-for-it.sh wait-for-it.sh

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "mysql", "3306", "java", "-jar", "app.jar"]
