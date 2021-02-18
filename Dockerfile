FROM amazoncorretto:11
WORKDIR /app
ADD target/kafka-service.jar kafka-service.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/kafka-service.jar"]
