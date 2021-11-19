package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void testPetCreate() {
        given().contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .formParam("pet_type","Dog")
                        .formParam("pet_age",11)
                        .formParam("pet_name","Black")
                .when().post("http://localhost:8080/v1/pets/add")
                .then()
                .statusCode(200);
        given().contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .formParam("pet_type","Cat")
                        .formParam("pet_age",9)
                        .formParam("pet_name","White")
                .when().post("http://localhost:8080/v1/pets/add")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetGetById() {
        given()
                .when().get("http://localhost:8080/v1/pets/1")
                .then()
                .statusCode(200);
    }
	@Test
    public void testPetGetByName() {
        given()
          .when().get("http://localhost:8080/v1/pets/name/Black")
          .then()
             .statusCode(200);
    }
    @Test
    public void testPetGetByAge() {
        given()
                .when().get("http://localhost:8080/v1/pets/age/11")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetGetByType() {
        given()
                .when().get("http://localhost:8080/v1/pets/type/Dog")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetUpdate() {
        given().contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .formParam("pet_type","Parrot")
                        .formParam("pet_age",2)
                        .formParam("pet_name","Green")
                        .formParam("pet_id","1")
                .when().put("http://localhost:8080/v1/pets/update")
                .then()
                .statusCode(200);
    }
    @Test
    public void testPetDelete() {
        given()
                .when().delete("http://localhost:8080/v1/pets/1")
                .then()
                .statusCode(200);
    }
}