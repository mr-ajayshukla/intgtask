use uos;

create table customer 
(
id bigint not null auto_increment, 
address varchar(255), 
email varchar(64), 
firstname varchar(64), 
region varchar(64), 
status varchar(16), 
surname varchar(64), 
zip_code varchar(16), 
primary key (id)
) engine=InnoDB;

create table orders 
(
order_id bigint not null auto_increment, 
amount double precision, 
customer_id bigint not null, 
date date, 
primary key (order_id),
CONSTRAINT fk_orders_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
) engine=InnoDB;

