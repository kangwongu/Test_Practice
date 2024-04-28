DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id int NOT NULL AUTO_INCREMENT,
    order_status varchar(10) DEFAULT NULL,
    total_price int DEFAULT NULL,
    registered_date_time datetime,
    created_date_time datetime,
    modified_date_time datetime,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_product;

CREATE TABLE order_product (
    id int NOT NULL AUTO_INCREMENT,
    order_id int DEFAULT NULL,
    product_id int DEFAULT NULL,
    created_date_time datetime,
    modified_date_time datetime,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id int NOT NULL AUTO_INCREMENT,
    product_number varchar(15) DEFAULT NULL,
    type varchar(10) DEFAULT NULL,
    selling_status varchar(50),
    name varchar(20),
    price int,
    created_date_time datetime,
    modified_date_time datetime,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS stock;

CREATE TABLE stock (
   id int NOT NULL AUTO_INCREMENT,
   product_number varchar(255),
   quantity int,
   created_date_time datetime,
   modified_date_time datetime,
   PRIMARY KEY (id)
);