package com.db.system.auction.service;

import com.db.system.auction.dao.AuctionBidDao;
import com.db.system.auction.dao.AuctionDao;
import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.data.model.auction.AuctionBid;
import com.db.system.data.model.auction.AuctionBidNew;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.Optional;

@Singleton
public class AuctionDetailsServiceImpl implements AuctionDetailsService {
    private final AuctionDao auctionDao;
    private final AuctionBidDao auctionBidDao;

    @Inject
    public AuctionDetailsServiceImpl(AuctionDao auctionDao, AuctionBidDao auctionBidDao) {
        this.auctionDao = auctionDao;
        this.auctionBidDao = auctionBidDao;
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

        Auction auction = auctionDao.get(auctionId);

        List<AuctionBid> bids = auctionBidDao.getBidsForAuction(auctionId);
        Optional<AuctionBid> auctionBidOptional = bids.stream().filter(b -> b.getPrice() >= auction.getMinPrice()).findFirst();

        return auctionBidOptional.orElse(null);
    }

    @Override
    public List<Auction> getAuctions() {
        List<Auction> auctions = auctionDao.getAllActive();
        auctions.forEach(a -> a.setMinPrice(null));
        return auctions;
    }

    @Override
    public AuctionBid bidAuction(AuctionBidNew auctionBidNew, Integer userId) {
        Auction auction = auctionDao.get(auctionBidNew.getAuctionId());
        if(auction.isActive()) {
            AuctionBid auctionBid = new AuctionBid();
            auctionBid.setAuctionId(auctionBidNew.getAuctionId());
            auctionBid.setPrice(auctionBidNew.getPrice());
            auctionBid.setUserId(userId);
            return auctionBidDao.addOrReplaceBid(auctionBid);
        }
        throw new WebApplicationException("Cannot bid on ended auction");
    }
}

