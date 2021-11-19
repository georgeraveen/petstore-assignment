package com.example.petstore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {


	List<Pet> pets = new ArrayList<Pet>(){
		{

			Pet pet1 = new Pet();
			pet1.setPetId(1);
			pet1.setPetAge(11);
			pet1.setPetName("Black");
			pet1.setPetType("Dog");
			add(pet1);
		}
	};


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("get-sample")
	public Response getSamplePets() {

		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		return Response.ok(pets).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0 || petId>pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pets.get(petId-1)).build();
		
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
	@POST
	@Path("add")
	public Response addPet(@FormParam("pet_type") String pet_type,
						   @FormParam("pet_name" ) String pet_name,
						   @FormParam("pet_age") int pet_age
						   ) {
		int pet_id=pets.size()+1;
		Pet pet = new Pet();
		pet.setPetId(pet_id);
		pet.setPetAge(pet_age);
		pet.setPetName(pet_name);
		pet.setPetType(pet_type);
		pets.add(pet);
		return Response.ok(pets.get(pet_id-1)).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@PUT
	@Path("update")
	public Response updatePet(@FormParam("pet_type") String pet_type,
						   @FormParam("pet_name" ) String pet_name,
						   @FormParam("pet_age") int pet_age,
							  @FormParam("pet_id") int pet_id
	) {
		if (pet_id < 0 || pet_id>pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(pet_id);
		pet.setPetAge(pet_age);
		pet.setPetName(pet_name);
		pet.setPetType(pet_type);
		pets.set(pet_id-1,pet);
		return Response.ok(pets.get(pet_id-1)).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@DELETE
	@Path("{petId}")
	public Response deletePet(@PathParam("petId") int petId) {
		if (petId < 0 || petId>pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		pets.remove(petId-1);
		return Response.ok().build();

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet by name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the name.") })
	@GET
	@Path("name/{petName}")
	public Response getPetName(@PathParam("petName") String petName) {
		List<Pet> result=pets.stream().filter(pet -> pet.getPetName().equals(petName)).collect(Collectors.toList());
		if(result.size()>0)
			return Response.ok(result).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet by age", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the age.") })
	@GET
	@Path("age/{petAge}")
	public Response getPetAge(@PathParam("petAge") int petAge) {
		List<Pet> result=pets.stream().filter(pet -> pet.getPetAge()==petAge).collect(Collectors.toList());
		if(result.size()>0)
			return Response.ok(result).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet by type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the type.") })
	@GET
	@Path("type/{petType}")
	public Response getPetType(@PathParam("petType") String petType) {
		List<Pet> result=pets.stream().filter(pet -> pet.getPetType().equals(petType)).collect(Collectors.toList());
		if(result.size()>0)
			return Response.ok(result).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
}
