package com.db.system.user.resource.auction;

import com.db.system.user.data.model.Auction;
import com.db.system.user.data.model.AuctionAdd;
import com.db.system.user.data.model.AuctionBid;
import com.db.system.user.data.model.AuctionBidNew;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1")
public interface AuctionResource {
    @POST
    @Path("/auction/add")
    @Produces(MediaType.APPLICATION_JSON)
    Auction addAuction(AuctionAdd auction);

    @POST
    @Path("/auction/{auctionId}/end")
    @Produces(MediaType.APPLICATION_JSON)
    AuctionBid endAuction(@PathParam("auctionId") Integer auctionId);

    @GET
    @Path("/auctions")
    @Produces(MediaType.APPLICATION_JSON)
    List<Auction> getAuctions();

    @POST
    @Path("/auction/bid/user/{userId}")
    AuctionBid bidAuction(AuctionBidNew auctionBidNew, @PathParam("userId") Integer userId);
}
