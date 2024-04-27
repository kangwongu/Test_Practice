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

insert into product(product_number, type, selling_status, name, price)
values ('001', 'HANDMADE', 'SELLING', '아메리카노', 4000),
       ('002', 'HANDMADE', 'HOLD', '카페라떼', 4500),
       ('003', 'BAKERY', 'STOP_SELLING', '크루아상', 3500);