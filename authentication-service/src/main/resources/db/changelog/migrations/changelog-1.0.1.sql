--liquibase formatted sql

--changeset artsem:1
--comment create table 'user_details'

create table public.user_details
(
    id       bigserial
        constraint user_details_pk
            primary key,
    email    varchar
        constraint user_details_pk_2
            unique,
    password varchar
);
