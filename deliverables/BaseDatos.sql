-- Database: challenge

DROP DATABASE IF EXISTS challenge;

CREATE DATABASE challenge;

create type account_type as enum(
	'SAVING',
	'CHECKING'
);

CREATE CAST (varchar AS account_type) WITH INOUT AS IMPLICIT;

create type transaction_type as enum(
	'DEBIT',
	'CREDIT'
);

CREATE CAST (varchar AS transaction_type) WITH INOUT AS IMPLICIT;

CREATE TABLE person(
   id serial NOT NULL,
   name varchar(50) NOT NULL,
   gender varchar(50) NOT NULL,
   age integer NOT NULL,
   identification varchar(13) NOT NULL,
   address varchar(100) NOT NULL,
   phone varchar(10) NOT NULL,
   PRIMARY KEY (id),
   unique(identification)
);

CREATE TABLE client
(
    client_id serial NOT NULL,
    password character varying(50) NOT NULL,
    status boolean NOT NULL,
    PRIMARY KEY (client_id),
    CONSTRAINT fk_person_client FOREIGN KEY (client_id) REFERENCES person (id)
);

CREATE TABLE account
(
    id serial NOT NULL,
    "number" character varying(10) NOT NULL,
    type account_type NOT NULL,
    balance numeric(10, 2) NOT NULL,
    state boolean NOT NULL,
    client_id int NOT NULL,
    PRIMARY KEY (id),
    unique("number"),
    CONSTRAINT fk_account_client FOREIGN KEY (client_id) REFERENCES client (client_id)
);

create table transaction(
                            serial int not null,
                            created_date date not null,
                            type transaction_type not null,
                            amount numeric(10,2) not null,
                            before_balance numeric(10,2) not null,
                            after_balance numeric(10,2) not null,
                            account_id int not null,
                            state boolean not null,
                            primary key(id),
                            constraint fk_transaction_account foreign key (account_id) references account(id)
)
    