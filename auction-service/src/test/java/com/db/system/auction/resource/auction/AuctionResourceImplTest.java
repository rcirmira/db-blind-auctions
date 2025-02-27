package com.db.system.auction.resource.auction;

import com.db.system.auction.config.database.DatabaseConfig;
import com.db.system.auction.dao.AuctionBidDao;
import com.db.system.auction.dao.AuctionDao;
import com.db.system.auction.service.AuctionDetailsService;
import com.db.system.auction.service.AuctionDetailsServiceImpl;
import com.db.system.data.model.auction.Auction;
import com.db.system.data.model.auction.AuctionAdd;
import com.db.system.resource.auction.AuctionResource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class AuctionResourceImplTest {
    private AuctionResource auctionResource;

    @BeforeClass
    public void setUp() {
        DatabaseConfig.getDataSource();
        AuctionDao auctionDao = new AuctionDao();
        AuctionBidDao auctionBidDao = new AuctionBidDao();
        AuctionDetailsService auctionDetailsService = new AuctionDetailsServiceImpl(auctionDao, auctionBidDao);
        auctionResource = new AuctionResourceImpl(auctionDetailsService);
    }

    @Test
    public void addAuctionSuccessTest() {
        List<Auction> auctions = auctionResource.getAuctions();
        assertNotNull(auctions);
        assertEquals(auctions.size(), 0);

        AuctionAdd auctionAdd = new AuctionAdd();
        auctionAdd.setName("someAuction");
        auctionAdd.setMinPrice(100);
        Auction auction = auctionResource.addAuction(auctionAdd);
        assertNotNull(auction);
        assertEquals(auction.getId(), 1);
        assertEquals(auction.getName(), "someAuction");
        assertEquals(auction.getMinPrice(), 100);
        assertTrue(auction.isActive());

        auctions = auctionResource.getAuctions();
        assertNotNull(auctions);
        assertEquals(auctions.size(), 1);
        Auction auctionGet = auctions.get(0);
        assertNotNull(auctionGet);
        assertEquals(auctionGet.getId(), 1);
        assertEquals(auctionGet.getName(), "someAuction");
        assertNull(auctionGet.getMinPrice());
        assertTrue(auctionGet.isActive());
    }
}
