package com.db.system.auction.dao;

import com.db.system.data.model.auction.Auction;

import java.util.List;

public interface AuctionMapper {
    Auction get(Integer auctionId);
    void add(Auction auction);
    void end(Integer auctionId);
    List<Auction> getAllActive();
}
