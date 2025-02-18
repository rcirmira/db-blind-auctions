package com.db.system.backend.gateway.resource;

import com.db.system.user.data.model.*;
import com.db.system.user.resource.auction.AuctionResource;
import com.db.system.user.resource.user.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.util.List;

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

    @Override
    public Auction addAuction(AuctionAdd auctionAdd, String userToken) {
        try(Client client = getJerseyClient()) {
            UserResource userResource = WebResourceFactory.newResource(UserResource.class, getUserResourceTarget(client));
            Response response = userResource.getUserByToken(userToken);
            if(response.getStatus() == Response.Status.OK.getStatusCode()) {
                AuctionResource auctionResource = WebResourceFactory.newResource(AuctionResource.class, getAuctionResourceTarget(client));
                return auctionResource.addAuction(auctionAdd);
            }
        }
        throw new WebApplicationException("Cannot add auction");
    }

    @Override
    public AuctionBid endAuction(Integer auctionId, String userToken) {
        try(Client client = getJerseyClient()) {
            UserResource userResource = WebResourceFactory.newResource(UserResource.class, getUserResourceTarget(client));
            Response response = userResource.getUserByToken(userToken);
            if(response.getStatus() == Response.Status.OK.getStatusCode()) {
                AuctionResource auctionResource = WebResourceFactory.newResource(AuctionResource.class, getAuctionResourceTarget(client));
                return auctionResource.endAuction(auctionId);
            }
        }
        throw new WebApplicationException("Cannot end auction");
    }

    @Override
    public List<Auction> getAuctions() {
        try(Client client = getJerseyClient()) {
            AuctionResource auctionResource = WebResourceFactory.newResource(AuctionResource.class, getUserResourceTarget(client));
            return auctionResource.getAuctions();
        }
    }

    @Override
    public AuctionBid bidAuction(AuctionBidNew auctionBidNew, String userToken) {
        try(Client client = getJerseyClient()) {
            UserResource userResource = WebResourceFactory.newResource(UserResource.class, getUserResourceTarget(client));
            Response response = userResource.getUserByToken(userToken);
            if(response.getStatus() == Response.Status.OK.getStatusCode()) {
                User user = response.readEntity(User.class);

                AuctionResource auctionResource = WebResourceFactory.newResource(AuctionResource.class, getAuctionResourceTarget(client));
                return auctionResource.bidAuction(auctionBidNew, user.getId());
            }
        }
        throw new WebApplicationException("Cannot bid on auction");
    }

    private WebTarget getUserResourceTarget(Client client) {
        return client.target("http://localhost:8086");
    }

    private WebTarget getAuctionResourceTarget(Client client) {
        return client.target("http://localhost:8087");
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
