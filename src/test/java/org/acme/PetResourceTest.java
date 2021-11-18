package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void testPetCreate() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("pet_name", "Virender");
        given()
                .when().post("http://localhost:8080/v1/pets/add",requestParams)
                .then()
                .statusCode(200);
//                .body(hasItem("petId"));
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("petId", "2")
////    		                hasEntry("pet_type", "Dog"),
////    		                hasEntry("pet_name", "Boola"),
////    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }
    @Test
    public void testPetGetById() {
        given()
                .when().get("http://localhost:8080/v1/pets/1")
                .then()
                .statusCode(200)
                .body(hasItem("petId"));
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("petId", "2")
////    		                hasEntry("pet_type", "Dog"),
////    		                hasEntry("pet_name", "Boola"),
////    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
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
        given()
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