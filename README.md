# PetStore Application

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

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

## Test API using curl
### Manage Pets
Create new pet

    curl --location --request POST 'localhost:8080/v1/pets/add' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'pet_type=Dog' \
    --data-urlencode 'pet_age=11' \
    --data-urlencode 'pet_name=Black'

View pet by ID

    curl --location --request GET 'localhost:8080/v1/pets/1'

View pet by name

    curl --location --request GET 'localhost:8080/v1/pets/name/Black'

View pet by age

    curl --location --request GET 'localhost:8080/v1/pets/age/11'

View pet by type

    curl --location --request GET 'localhost:8080/v1/pets/type/Dog'

Find by ID and update pet

    curl --location --request PUT 'localhost:8080/v1/pets/update' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'pet_type=Cat' \
    --data-urlencode 'pet_age=10' \
    --data-urlencode 'pet_name=White' \
    --data-urlencode 'pet_id=2'

Delete pet by ID

    curl --location --request DELETE 'localhost:8080/v1/pets/1' 

### Manage Pet Types
Create new type

    curl --location --request POST 'localhost:8080/v1/pet-types/add' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'pet_type=Dog' 

View type by ID

    curl --location --request GET 'localhost:8080/v1/pet-types/1'

Find by ID and update pet

    curl --location --request PUT 'localhost:8080/v1/pet-types/update' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'petType_type=Cat' \
    --data-urlencode 'petType_id=1' 

Delete pet by ID

    curl --location --request DELETE 'localhost:8080/v1/pet-types/1' 

## Test Suites
Test suites are available in test/java/org/acme/

    PetResourceTest.java
    PetTypeResourceTest.java

Run

    ./gradlew test

## Deploying Application

Create docker image

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true

Run image

    docker run -i --rm -p 8080:8080 georg/petstore:unspecified

Deploy on docker compose

    cd deploy
    docker-compose up -d

View dashboard at  http://localhost:3000/dashboards
