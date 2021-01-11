-- noinspection SqlNoDataSourceInspection

CREATE TABLE products(
	id           SERIAL         PRIMARY KEY,
	name         VARCHAR(100)   NOT NULL,
	description  VARCHAR(1000)  NOT NULL
);
