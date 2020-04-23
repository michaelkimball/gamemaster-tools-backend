# gamemaster-backend project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

This will also need a mongo database. You can start that on your local machine or through docker using:
```
docker run -d -p 27017:27017 -v ~/data:/data/db mongo
```
Alternatively, you can download Mongo [here](https://www.mongodb.com/download-center/community).

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `gamemaster-backend-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/gamemaster-backend-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/gamemaster-backend-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Building and running using docker-compose

You can run this application also using `docker-compose up`

The build process uses a multi-stage build. The first stage uses a Linux instance with GraalVM to build a native Linux executable. The second stage is a much lighter Linux image which the application is copied and run on.

## Tools used
Favicon generated from [favicon.io](https://favicon.io/)