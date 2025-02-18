package com.db.system.user.resource.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1")
@Tag(name = "User Service")
public interface UserResource {
    @Operation(summary = "Gets token for matching name")
    @Tag(name = "User token")
    @GET
    @Path("/user/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserToken(@PathParam("name") String name);

    @Operation(summary = "get user for passed token after validation")
    @Tag(name = "User token")
    @GET
    @Path("/user/token/get/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserByToken(@PathParam("userToken") String userToken);
}
