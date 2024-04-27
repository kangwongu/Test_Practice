DROP TABLE IF EXISTS product;

create table product (
     price integer not null,
     created_date_time datetime(6),
     id bigint not null auto_increment,
     modified_date_time datetime(6),
     name varchar(255),
     product_number varchar(255),
     selling_status varchar(255),
     type varchar(255),
     primary key (id)
);

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

insert into product(product_number, type, selling_status, name, price)
values ('001', 'HANDMADE', 'SELLING', '아메리카노', 4000),
       ('002', 'HANDMADE', 'HOLD', '카페라떼', 4500),
       ('003', 'BAKERY', 'STOP_SELLING', '크루아상', 3500);