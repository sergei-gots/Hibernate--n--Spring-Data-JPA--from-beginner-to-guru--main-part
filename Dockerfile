FROM eclipse-temurin:17-jre-alpine AS builder
WORKDIR /app
ADD target/kbe-rest-beer-0.0.1-SNAPSHOT.jar ./
RUN java -Djarmode=layertools -jar kbe-rest-beer-0.0.1-SNAPSHOT.jar extract

FROM eclipse-temurin:17-jre-alpine
ENV JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.edg=file:/dev/./urandom"
WORKDIR /app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]
