--liquibase formatted sql

--changeset artsem:2
--comment add unique key on 'isbn'

alter table book
    add constraint book_pk_2
        unique (isbn);
