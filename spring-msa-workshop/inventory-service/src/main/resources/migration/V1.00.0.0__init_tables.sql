/*
DROP TABLE inventory;
*/

CREATE TABLE `inventory`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '아이디',
    `sku_code` VARCHAR(100) NOT NULL COMMENT 'SKU 코드',
    `stock`    INT          NOT NULL COMMENT '재고',
    UNIQUE KEY `uk_inventory_001` (`sku_code`),
    PRIMARY KEY (id)
);

ALTER TABLE `inventory`
    COMMENT '재고 정보';