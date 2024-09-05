--liquibase formatted sql

--changeset artsem:2
--comment make 'id' not default

alter table public.books_status
    alter column id drop default;

drop sequence public.books_status_id_seq;

