package com.db.system.user.service.internal.model;

public class TokenMappingPair {
    private final Integer id;
    private final String name;

    public TokenMappingPair(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
