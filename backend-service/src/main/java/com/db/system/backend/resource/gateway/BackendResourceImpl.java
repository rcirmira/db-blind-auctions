package com.db.system.backend.resource.gateway;

import com.db.system.user.resource.user.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;

@Path("/api/v1")
@Tag(name = "User Service")
public class BackendResourceImpl implements BackendResource {
    @Override
    public Response getUserToken(String name) {
        try(Client client = getJerseyClient()) {
            UserResource userResource = WebResourceFactory.newResource(UserResource.class, getUserResourceTarget(client));
            return userResource.getUserToken(name);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private WebTarget getUserResourceTarget(Client client) {
        return client.target("http://localhost:8085");
    }

    private static Client getJerseyClient() {
        // Create Jersey client with Jackson JSON provider
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);
        client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        client.property(ClientProperties.READ_TIMEOUT, 10000);
        return client;
    }

    // TODO build jar with dependencies and add simple cmd execution steps in README

    // TODO implement following APIs
    // Register a new product for auction by a seller and specify a minimum bid
    // List the auctions in which it is possible to bid
    // Register a buyer’s bid in an auction any number of times
    // End the action and return the winning bid

    // TODO add swagger to make the API calls easier
    // TODO generate surfire reports
    // TODO generate jacoco reports
    // TODO add jacoco-results-aggreggate
    // TODO add jacoco-results-aggregate-check
}
