--liquibase formatted sql

--changeset artsem:1
--comment create table 'books_status'

create table public.books_status
(
    id          bigserial
        constraint books_status_pk
            primary key,
    is_free     boolean,
    take_date   timestamp,
    return_date timestamp
);
