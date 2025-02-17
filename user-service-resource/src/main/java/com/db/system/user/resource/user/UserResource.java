package com.db.system.user.resource.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/user")
@Tag(name = "User Service")
public interface UserResource {
    @Operation(summary = "Gets user for passed token")
    @Tag(name = "User token")
    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserToken(@PathParam("name") String name);

    @Operation(summary = "Validates passed user token")
    @Tag(name = "User token")
    @GET
    @Path("/valid/{userToken}")
    boolean isValidToken(@PathParam("userToken") String userToken);
}
