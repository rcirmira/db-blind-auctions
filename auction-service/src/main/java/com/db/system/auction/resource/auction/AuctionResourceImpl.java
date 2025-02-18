package com.db.system.auction.resource.auction;

import com.db.system.auction.service.AuctionDetailsService;
import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;
import com.db.system.resource.auction.AuctionResource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class AuctionResourceImpl implements AuctionResource {
    private final AuctionDetailsService auctionDetailsService;

    @Inject
    public AuctionResourceImpl(AuctionDetailsService auctionDetailsService) {
        this.auctionDetailsService = auctionDetailsService;
    }

    @Override
    public Auction addAuction(AuctionAdd auctionAdd) {
        return this.auctionDetailsService.addAuction(auctionAdd);
    }

    @Override
    public AuctionBid endAuction(Integer auctionId) {
        return auctionDetailsService.endAuction(auctionId);
    }

    @Override
    public List<Auction> getAuctions() {
        return auctionDetailsService.getAuctions();
    }

    @Override
    public AuctionBid bidAuction(AuctionBidNew auctionBidNew, Integer userId) {
        return auctionDetailsService.bidAuction(auctionBidNew,  userId);
    }
}
