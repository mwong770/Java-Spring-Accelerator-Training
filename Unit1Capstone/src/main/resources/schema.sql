
-- PRODUCTION DATABASE

create schema if not exists game_store;
use game_store;

create table if not exists game
(
	game_id int (11) not null auto_increment primary key,
    title varchar (50) not null,
    esrb_rating varchar (50) not null,
    description varchar (255) not null,
    price decimal (5, 2) not null,
    studio varchar (50) not null,
    quantity int (11) not null
);

create table if not exists console
(
	console_id int (11) not null auto_increment primary key,
    model varchar (50) not null,
    manufacturer varchar (50) not null,
    memory_amount varchar (20),
    processor varchar (20),
    price decimal (5, 2) not null,
    quantity int (11) not null
);

create table if not exists t_shirt
(
	t_shirt_id int (11) not null auto_increment primary key,
    size varchar (20) not null,
    color varchar (20) not null,
    description varchar (255) not null,
    price decimal (5,2) not null,
    quantity int (11) not null
);

create table if not exists sales_tax_rate
(
	state char (2) not null,
    rate decimal (3,2) not null
);

create unique index ix_state_rate on sales_tax_rate (state, rate);

create table if not exists processing_fee
(
	product_type varchar (20) not null,
    fee decimal (4,2)
);

create unique index ix_product_type_fee on processing_fee (product_type, fee);

create table if not exists invoice
(
	invoice_id int (11) not null auto_increment primary key,
    name varchar (80) not null,
    street varchar (30) not null,
    city varchar (30) not null,
    state varchar (30) not null,
    zipcode varchar (5) not null,
    item_type varchar (20) not null,
    item_id int (11) not null,
    unit_price decimal (5,2) not null,
    quantity int (11) not null,
    subtotal decimal (5,2) not null,
    tax decimal (5,2) not null,
    processing_fee decimal (5,2) not null,
    total decimal (5,2) not null
);

INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('AL', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('AK', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('AZ', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('AR', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('CA', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('CO', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('CT', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('DE', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('FL', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('GA', '0.07');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('HI', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('ID', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('IL', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('IN', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('IA', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('KS', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('KY', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('LA', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('ME', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MD', '0.07');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MA', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MI', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MN', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MS', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MO', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('MT', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NE', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NV', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NH', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NJ', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NM', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NY', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('NC', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('ND', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('OH', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('OK', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('OR', '0.07');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('PA', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('RI', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('SC', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('SD', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('TN', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('TX', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('UT', '0.04');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('VA', '0.06');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('VT', '0.07');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('WA', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('WV', '0.05');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('WI', '0.03');
INSERT INTO `game_store`.`sales_tax_rate` (`state`, `rate`) VALUES ('WY', '0.04');

INSERT INTO `game_store`.`processing_fee` (`product_type`, `fee`) VALUES ('Consoles', '14.99');
INSERT INTO `game_store`.`processing_fee` (`product_type`, `fee`) VALUES ('T-Shirts', '1.98');
INSERT INTO `game_store`.`processing_fee` (`product_type`, `fee`) VALUES ('Games', '1.49');

-- TESTING DATABASE

create schema if not exists game_store_test;
use game_store_test;

create table if not exists game
(
	game_id int (11) not null auto_increment primary key,
    title varchar (50) not null,
    esrb_rating varchar (50) not null,
    description varchar (255) not null,
    price decimal (5, 2) not null,
    studio varchar (50) not null,
    quantity int (11)
);

create table if not exists console
(
	console_id int (11) not null auto_increment primary key,
    model varchar (50) not null,
    manufacturer varchar (50) not null,
    memory_amount varchar (20),
    processor varchar (20),
    price decimal (5, 2) not null,
    quantity int (11) not null
);

create table if not exists t_shirt
(
	t_shirt_id int (11) not null auto_increment primary key,
    size varchar (20) not null,
    color varchar (20) not null,
    description varchar (255) not null,
    price decimal (5,2) not null,
    quantity int (11) not null
);

create table if not exists sales_tax_rate
(
	state char (2) not null,
    rate decimal (3,2) not null
);

create unique index ix_state_rate on sales_tax_rate (state, rate);

create table if not exists processing_fee
(
	product_type varchar (20) not null,
    fee decimal (4,2)
);

create unique index ix_product_type_fee on processing_fee (product_type, fee);

create table if not exists invoice
(
	invoice_id int (11) not null auto_increment primary key,
    name varchar (80) not null,
    street varchar (30) not null,
    city varchar (30) not null,
    state varchar (30) not null,
    zipcode varchar (5) not null,
    item_type varchar (20) not null,
    item_id int (11) not null,
    unit_price decimal (5,2) not null,
    quantity int (11) not null,
    subtotal decimal (5,2) not null,
    tax decimal (5,2) not null,
    processing_fee decimal (5,2) not null,
    total decimal (5,2) not null
);

INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('AL', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('AK', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('AZ', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('AR', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('CA', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('CO', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('CT', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('DE', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('FL', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('GA', '0.07');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('HI', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('ID', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('IL', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('IN', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('IA', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('KS', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('KY', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('LA', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('ME', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MD', '0.07');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MA', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MI', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MN', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MS', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MO', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('MT', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NE', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NV', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NH', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NJ', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NM', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NY', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('NC', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('ND', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('OH', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('OK', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('OR', '0.07');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('PA', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('RI', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('SC', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('SD', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('TN', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('TX', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('UT', '0.04');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('VA', '0.06');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('VT', '0.07');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('WA', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('WV', '0.05');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('WI', '0.03');
INSERT INTO `game_store_test`.`sales_tax_rate` (`state`, `rate`) VALUES ('WY', '0.04');

INSERT INTO `game_store_test`.`processing_fee` (`product_type`, `fee`) VALUES ('Consoles', '14.99');
INSERT INTO `game_store_test`.`processing_fee` (`product_type`, `fee`) VALUES ('T-Shirts', '1.98');
INSERT INTO `game_store_test`.`processing_fee` (`product_type`, `fee`) VALUES ('Games', '1.49');
