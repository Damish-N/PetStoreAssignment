### Damish Nisal
#### 18001599
#### SCS3203 / IS3108 Middleware Architecture
# MicroProfile generated Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petStore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Creating a native executable

Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation.

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

You can then execute your native executable with:

    ./build/petStore-runner

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

### Open Swagger

To launch API doc Run the project and route with below url
   
     http://localhost:8080/swagger-ui/

Exposes the information about your endpoints in the format of the OpenAPI v3 specification. Specification [here](https://microprofile.io/project/eclipse/microprofile-open-api)

The index page contains a link to the OpenAPI information of your endpoints.

## To build and deploy the API

Run the following commands and up the swagger and refer the End points

##### step 1:
run the command on terminal(root level of project)

    ./gradlew build -Dquarkus.package.type=uber-jar

##### step 2
run the command on terminal (root level of project)

    java -jar build/petStore-runner.jar

##### step 3
to find the End-points on here

    http://localhost:8080/swagger-ui/

## Run Test Suite
run the command on terminal (root level of project)

    ./gradlew test

## CURL/WGET commands

### Pets 
Get the all the pets

    curl -X GET "http://localhost:8080/pets" -H  "accept: application/json"

Create a new pet

    curl -X POST "http://localhost:8080/pets" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"petAge\":3,\"petName\":\"doggy\",\"petType\":\"dog\"}"
    
Update an existing pet

    curl -X PUT "http://localhost:8080/pets/1" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"petAge\":3,\"petName\":\"dikky\",\"petType\":\"dog\"}"

Delete an existing pet

    curl -X DELETE "http://localhost:8080/pets/3" -H  "accept: */*"

Search pets with name/type/age

    curl -X GET "http://localhost:8080/pets/search?age=5&name=boola&type=dog" -H  "accept: application/json"

Get pet by ID
    
    curl -X GET "http://localhost:8080/pets/3" -H  "accept: application/json"

### Pet Types

Get all pet types

    curl -X GET "http://localhost:8080/petTypes" -H  "accept: application/json"

Create a new pet type

    curl -X POST "http://localhost:8080/petTypes" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"petId\":4,\"petType\":\"lion\"}"

Update an existing pet type

    curl -X PUT "http://localhost:8080/petTypes/1" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"petId\":1,\"petType\":\"dogs\"}"

Delete an existing  pet type

    curl -X DELETE "http://localhost:8080/petTypes/1" -H  "accept: application/json"

#### Schemas
Pet Schema

    {
    "petAge": int,
    "petId": int,
    "petName": "string",
    "petType": "string" 
    }

PetType Schema
    
    {
    "petId": int,
    "petType": "string"
    }

Pet Request Schema

    {
    "petAge": int,
    "petName": "string",
    "petType": "string"
    }
    
