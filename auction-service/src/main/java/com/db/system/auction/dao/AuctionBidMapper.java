package com.db.system.auction.dao;

import com.db.system.data.model.auction.AuctionBid;

import java.util.List;

public interface AuctionBidMapper {
    void addOrReplaceBid(AuctionBid auctionBid);
    List<AuctionBid> getBidsForAuction(Integer auctionId);
}
