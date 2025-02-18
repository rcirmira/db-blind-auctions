package com.db.system.user.service;

import com.db.system.data.model.user.User;
import com.db.system.user.service.internal.model.TokenMappingPair;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.util.Date;

@Singleton
public class JwtServiceImpl implements JwtService {
    private static final Logger LOG = LoggerFactory.getLogger(JwtServiceImpl.class);
    private static final long MAX_AGE_MILLISECONDS = 10_800_000; // 3h
    private final SignatureAlgorithm alg;
    private final KeyPair pair;

    public JwtServiceImpl() {
        // Create a test key suitable for the desired ECDSA signature algorithm:
        this.alg = Jwts.SIG.ES512;
        this.pair = alg.keyPair().build();
    }

    @Override
    public String getToken(User user) {
        LOG.debug("getToken() -> user = {}", user);

        String token = Jwts.builder().subject(user.getName())
                .expiration(new Date(System.currentTimeMillis() + MAX_AGE_MILLISECONDS))
                .signWith(pair.getPrivate(), alg)
                .claims().add("id", user.getId()).and()
                .compact();


        LOG.debug("getToken() -> user = {}, token = {}", user, token);

        return token;

    }

    @Override
    public TokenMappingPair getTokenMappingPair(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(pair.getPublic())
                    .build().parseSignedClaims(token);

            String name = claims.getPayload().getSubject();
            Integer id = claims.getPayload().get("id", Integer.class);

            if(claims.getPayload().getExpiration().before(new Date())) {
                LOG.warn("Token for user id {} and name {} expired", id, name);
                return null;
            } else {
                return new TokenMappingPair(id, name);
            }
        } catch (Exception e) {
            LOG.error("JWT parsing error", e);
            return null;
        }
    }
}
