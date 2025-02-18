package com.db.system.backend.config.swagger;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

@Path("/")
@Tag(name = "Swagger APIs")
public class SwaggerUiResource {
    private static final Logger LOG = LoggerFactory.getLogger(SwaggerUiResource.class);

    @GET
    @Produces("text/html")
    @Path("/swagger-ui")
    public Response getSwaggerUiIndex() {
        return serveFile("swagger-ui/index.html");
    }

    @GET
    @Path("/{file}")
    public Response getStaticFile(@PathParam("file") String file) {
        return serveFile("swagger-ui/" + file);
    }

    private Response serveFile(String filePath) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (stream == null) {
            LOG.warn("File not found: {}", filePath);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(stream).build();
    }
}
