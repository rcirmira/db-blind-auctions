package com.db.system.auction.dao;

import com.db.system.auction.config.database.MyBatisConfig;
import com.db.system.data.model.auction.Auction;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Singleton
public class AuctionDao {
    private final SqlSessionFactory sqlSessionFactory;

    public AuctionDao() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    public void add(Auction auction) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionMapper mapper = session.getMapper(AuctionMapper.class);
            mapper.add(auction);
        }
    }

    public void end(Integer auctionId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionMapper mapper = session.getMapper(AuctionMapper.class);
            mapper.end(auctionId);
        }
    }

    public List<Auction> getAllActive() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionMapper mapper = session.getMapper(AuctionMapper.class);
            return mapper.getAllActive();
        }
    }

    public Auction get(Integer auctionId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuctionMapper mapper = session.getMapper(AuctionMapper.class);
            return mapper.get(auctionId);
        }
    }
}
