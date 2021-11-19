package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class PetTypeResourceTest {

    @Test
    public void testPetTypeCreate() {

        given().contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .formParam("pet_type","Dog")
                .when().post("http://localhost:8080/v1/pet-types/add")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetTypeGetById() {
        given()
                .when().get("http://localhost:8080/v1/pet-types/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPetTypeUpdate() {
        given().contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .formParam("petType_type","Cat")
                .formParam("petType_id",1)
                .when().put("http://localhost:8080/v1/pet-types/update")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetTypeDelete() {
        given()
                .when().delete("http://localhost:8080/v1/pet-types/1")
                .then()
                .statusCode(200);
    }
}