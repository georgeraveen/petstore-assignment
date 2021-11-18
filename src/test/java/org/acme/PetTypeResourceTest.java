package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class PetTypeResourceTest {

    @Test
    public void testPetCreate() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("pet_name", "Virender");
        given()
                .when().post("http://localhost:8080/v1/pet-types/add",requestParams)
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetGetById() {
        given()
                .when().get("http://localhost:8080/v1/pet-types/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPetUpdate() {
        given()
                .when().put("http://localhost:8080/v1/pet-types/update")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetDelete() {
        given()
                .when().delete("http://localhost:8080/v1/pet-types/1")
                .then()
                .statusCode(200);
    }
}