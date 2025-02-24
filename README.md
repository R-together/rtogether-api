# RTogether-api

Backend API for RTogether web application

## Dependencies

Before starting, make sure you have the following dependencies installed:

- [Maven](https://maven.apache.org/install.html)
- [Java 17](https://adoptium.net/?variant=openjdk17)
- [Docker](https://docs.docker.com/get-docker/) or [Podman](https://podman.io/getting-started/installation)
- [Docker Compose](https://docs.docker.com/compose/install/) or [Podman Compose](https://github.com/containers/podman-compose)

## Configuration

1. Create a `.env` file in the root directory of the project based on the `.env.example` file.

```sh
cp .env.example .env
```

## Running the Project
### Using Docker
Make the build-and-run.sh script executable:
```sh
chmod +x build-and-run.sh
```
Execute the script to build and run the project:
```sh
./build-and-run.sh
```
### Using Podman
Make the podman-build-and-run.sh script executable:
```sh
chmod +x podman-build-and-run.sh
```
Execute the script to build and run the project:
```sh
./podman-build-and-run.sh
```

### Manual Execution
If you prefer not to use the scripts, follow these steps:

1. Build the project using Maven:
```sh
mvn clean package -DskipTests
```
2. Build the Docker (or Podman) image:
```sh
docker-compose build
# or
podman-compose build
```
3. Start the containers:
```sh
docker-compose up -d
# or
podman-compose up -d
```

5. Execute the tests:
```sh
mvn test
```
