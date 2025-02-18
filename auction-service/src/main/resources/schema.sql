CREATE TABLE auction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    min_price INT NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE auction_bid (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    auction_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    price int NOT NULL,
    CONSTRAINT unique_user_auction UNIQUE (auction_id, user_id)
);
