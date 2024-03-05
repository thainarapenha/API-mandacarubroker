CREATE TABLE stock (
                       id varchar(255) primary key,
                       symbol varchar(255) not null,
                       company_name varchar(255) not null,
                       price float not null
);

CREATE TABLE users (
                       id varchar(255) primary key,
                       name varchar(255) not null
)