--liquibase formatted sql

--changeset artsem:1
--comment create table 'book'

create table book
(
    id          bigserial
        constraint book_pk
            primary key,
    isbn        varchar,
    name        varchar,
    description varchar,
    genre       varchar,
    author      varchar
);
