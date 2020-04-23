FROM thinkco/graalvm
WORKDIR /etc/gamemaster
COPY mvnw mvnw.cmd pom.xml README.md ./
COPY src ./src
COPY .mvn ./.mvn
RUN ./mvnw package -Pnative

FROM debian:stable-slim
WORKDIR /etc/gamemaster
COPY --from=0 /etc/gamemaster/target/gamemaster-backend-1.0-SNAPSHOT-runner ./application
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]