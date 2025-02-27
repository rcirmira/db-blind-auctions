package com.db.system.backend.config.hk2;

import com.db.system.backend.config.swagger.SwaggerUiResource;
import com.db.system.backend.gateway.resource.BackendResourceImpl;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JacksonFeature.class); // Enable JSON support

        // Register OpenAPI endpoint
        register(OpenApiResource.class);

        // Register API resources
        register(SwaggerUiResource.class);
        register(BackendResourceImpl.class);

        // Configure OpenAPI metadata
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("User API")
                        .version("1.0")
                        .description("API for managing users"));

        // Add the OpenAPI instance to the Jersey context
        property("swagger.openapi.info", openAPI);

        // Disable wadl to avoid warning print-outs in the log
        property("jersey.config.server.wadl.disableWadl", true);

        // Register HK2 dependency injection
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.bind(locator, new BackendServiceBinder());

        // Directly register the HK2 binder
        register(new BackendServiceBinder());
    }
}
