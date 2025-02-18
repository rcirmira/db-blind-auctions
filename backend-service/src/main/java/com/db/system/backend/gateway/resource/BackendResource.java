package com.db.system.backend.gateway.resource;

import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/backend")
@Tag(name = "Backend Service")
public interface BackendResource {
    @Operation(summary = "Gets user for passed token")
    @GET
    @Path("/user/get/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    Response getUserToken(@PathParam("name") String name);

    @Operation(summary = "Creates new auction product with a minimum bid")
    @POST
    @Path("/auction/add/user/token/{userToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Auction addAuction(AuctionAdd auctionAdd, @PathParam("userToken") String userToken);

    @Operation(summary = "End the auction")
    @POST
    @Path("/auction/{auctionId}/end/user/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    AuctionBid endAuction(@PathParam("auctionId") Integer auctionId, @PathParam("userToken") String userToken);

    @Operation(summary = "Get all the active auctions")
    @GET
    @Path("/auctions")
    @Produces(MediaType.APPLICATION_JSON)
    List<Auction> getAuctions();

    @Operation(summary = "Bid on an auction")
    @POST
    @Path("/auction/bid/user/token/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    AuctionBid bidAuction(AuctionBidNew auctionBidNew, @PathParam("userToken") String userToken);
}
