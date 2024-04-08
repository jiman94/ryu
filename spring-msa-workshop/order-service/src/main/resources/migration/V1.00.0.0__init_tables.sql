/*
DROP TABLE order;
DROP TABLE order_item;
*/

CREATE TABLE `order`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '아이디',
    `order_code`    VARCHAR(50)  NOT NULL COMMENT '주문 코드',
    `order_user_id` BIGINT       NOT NULL COMMENT '주문자 아이디 번호',
    `order_date`    DATE         NOT NULL COMMENT '주문 일자',
    `order_status`  VARCHAR(100) NOT NULL COMMENT '주문 상태',
    `deleted`       TINYINT(1)   NOT NULL DEFAULT '0' COMMENT '삭제여부',
    `create_user`   BIGINT       NOT NULL COMMENT '등록자',
    `create_date`   DATETIME(6)  NOT NULL COMMENT '등록일시',
    `update_user`   BIGINT       NOT NULL DEFAULT 0 COMMENT '수정자',
    `update_date`   DATETIME(6)  NOT NULL DEFAULT '2999-12-31 00:00:00.000000' COMMENT '수정일시',
    PRIMARY KEY (id)
);

ALTER TABLE `order`
    COMMENT '주문 정보';

CREATE INDEX ix_order_001 ON `order` (order_code);

-- 테이블 생성 SQL - review
CREATE TABLE `order_item`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '아이디',
    `order_id`        BIGINT       NOT NULL COMMENT '주문 아이디',
    `order_item_name` VARCHAR(100) NOT NULL COMMENT '주문 아이템명',
    `sku_code`        VARCHAR(100) NOT NULL COMMENT 'SKU 코드',
    `price`           VARCHAR(100) NOT NULL COMMENT '가격',
    PRIMARY KEY (id)
);

-- 테이블 Comment 설정 SQL - review
ALTER TABLE order_item
    COMMENT '주문 아이템';

CREATE INDEX ix_order_item_001 ON `order_item` (order_id);
CREATE INDEX ix_order_item_002 ON `order_item` (sku_code);