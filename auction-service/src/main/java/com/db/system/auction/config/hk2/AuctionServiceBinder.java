package com.db.system.auction.config.hk2;

import com.db.system.auction.dao.AuctionBidDao;
import com.db.system.auction.dao.AuctionDao;
import com.db.system.auction.resource.auction.AuctionResourceImpl;
import com.db.system.auction.service.AuctionDetailsService;
import com.db.system.auction.service.AuctionDetailsServiceImpl;
import com.db.system.resource.auction.AuctionResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class AuctionServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // DAOs
        bind(AuctionDao.class).to(AuctionDao.class);
        bind(AuctionBidDao.class).to(AuctionBidDao.class);

        // services
        bind(AuctionDetailsServiceImpl.class).to(AuctionDetailsService.class);

        // resources
        bind(AuctionResourceImpl.class).to(AuctionResource.class);
    }
}

