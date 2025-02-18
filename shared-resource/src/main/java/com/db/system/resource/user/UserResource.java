package com.db.system.resource.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1")
public interface UserResource {
    @GET
    @Path("/user/get/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    Response getUserToken(@PathParam("name") String name);

    @GET
    @Path("/user/token/get/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserByToken(@PathParam("userToken") String userToken);
}
