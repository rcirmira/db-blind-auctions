package com.db.system.auction.service;

import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;

import java.util.List;

public interface AuctionDetailsService {
    Auction addAuction(AuctionAdd auctionAdd);
    AuctionBid endAuction(Integer auctionId);
    List<Auction> getAuctions();
    AuctionBid bidAuction(AuctionBidNew auctionBidNew, Integer userId);
}
