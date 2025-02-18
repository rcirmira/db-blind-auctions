package com.db.system.resource.auction;

import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1")
public interface AuctionResource {
    @POST
    @Path("/auction/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Auction addAuction(AuctionAdd auctionAdd);

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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    AuctionBid bidAuction(AuctionBidNew auctionBidNew, @PathParam("userId") Integer userId);
}
