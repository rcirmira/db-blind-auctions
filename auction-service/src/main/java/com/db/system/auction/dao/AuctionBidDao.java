package com.db.system.auction.dao;

import com.db.system.auction.config.database.MyBatisConfig;
import com.db.system.data.model.auction.AuctionBid;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Singleton
public class AuctionBidDao {
    private final SqlSessionFactory sqlSessionFactory;

    public AuctionBidDao() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    public AuctionBid addOrReplaceBid(AuctionBid auctionBid) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionBidMapper mapper = session.getMapper(AuctionBidMapper.class);
            mapper.addOrReplaceBid(auctionBid);
            return auctionBid;
        }
    }

    public List<AuctionBid> getBidsForAuction(Integer auctionId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionBidMapper mapper = session.getMapper(AuctionBidMapper.class);
            return mapper.getBidsForAuction(auctionId);
        }
    }
}
