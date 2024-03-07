CREATE TABLE users (
                       id varchar(255) primary key,
                       email varchar(255) not null unique,
                       username varchar(255) not null unique,
                       password varchar(255) not null,
                       first_name varchar(255) not null,
                       last_name varchar(255),
                       role varchar(255) not null,
                       birth_date date not null,
                       balance decimal not null
);
