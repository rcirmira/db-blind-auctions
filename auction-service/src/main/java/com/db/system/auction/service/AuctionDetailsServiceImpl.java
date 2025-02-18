package com.db.system.auction.service;

import com.db.system.auction.dao.AuctionDao;
import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class AuctionDetailsServiceImpl implements AuctionDetailsService {
    private final AuctionDao auctionDao;

    @Inject
    public AuctionDetailsServiceImpl(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    @Override
    public Auction addAuction(AuctionAdd auctionAdd) {
        Auction auction = new Auction();
        auction.setName(auctionAdd.getName());
        auction.setMinPrice(auctionAdd.getMinPrice());
        auction.setActive(true);

        auctionDao.add(auction);

        return auction;
    }

    @Override
    public AuctionBid endAuction(Integer auctionId) {
        auctionDao.end(auctionId);

        // TODO implement bid action!
        return null;
    }

    @Override
    public List<Auction> getAuctions() {
        return auctionDao.getAllActive();
    }

    @Override
    public AuctionBid bidAuction(AuctionBidNew auctionBidNew, Integer userId) {
        return null;
    }
}
