package com.db.system.user.resource.user;

import com.db.system.user.data.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/user")
@Tag(name = "User Service")
public interface UserResource {
    @Operation(summary = "Gets user for passed token")
    @Tag(name = "User account")
    @GET
    @Path("/get/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    User getAccountById(@PathParam("userToken") String userToken);
}
