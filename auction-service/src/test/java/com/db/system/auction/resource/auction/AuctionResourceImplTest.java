package com.db.system.auction.resource.auction;

import com.db.system.auction.config.database.DatabaseConfig;
import com.db.system.auction.dao.AuctionBidDao;
import com.db.system.auction.dao.AuctionDao;
import com.db.system.auction.service.AuctionDetailsService;
import com.db.system.auction.service.AuctionDetailsServiceImpl;
import com.db.system.data.model.auction.Auction;
import com.db.system.resource.auction.AuctionResource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    public void getAuctionsReturnsEmptyArrayTest() {
        List<Auction> auctions = auctionResource.getAuctions();
        assertNotNull(auctions);
        assertEquals(auctions.size(), 0);
    }
}
