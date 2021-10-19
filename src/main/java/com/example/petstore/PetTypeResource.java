package com.example.petstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pet-types")
@Produces("application/json")
public class PetTypeResource {

	List<String> petTypes = new ArrayList<String>(Arrays.asList("Dog","Cat","Bird"));




	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet type found for the id.") })
	@GET
	@Path("{petTypeId}")
	public Response getPetType(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId < 0 || petTypeId>petTypes.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(petTypes.get(petTypeId-1)).build();

	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add pet type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
	@POST
	@Path("add")
	public Response addPetType(@FormParam("pet_type") String pet_type) {
		int petType_id=petTypes.size()+1;
		petTypes.add(pet_type);
		return Response.ok(petTypes.get(petType_id-1)).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
			@APIResponse(responseCode = "404", description = "No petType found for the id.") })
	@PUT
	@Path("update")
	public Response updatePetType(@FormParam("petType_type") String petType,
							  @FormParam("petType_id") int petType_id
	) {
		if (petType_id < 0 || petType_id>petTypes.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		petTypes.set(petType_id-1,petType);
		return Response.ok(petTypes.get(petType_id-1)).build();
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete petType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
			@APIResponse(responseCode = "404", description = "No petType found for the id.") })
	@DELETE
	@Path("{petTypeId}")
	public Response deletePetType(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId < 0 || petTypeId>petTypes.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		petTypes.remove(petTypeId-1);
		return Response.ok().build();

	}
}
