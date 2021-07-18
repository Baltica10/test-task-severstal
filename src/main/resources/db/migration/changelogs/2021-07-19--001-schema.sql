--liquibase formatted sql

--changeset an.krasnov:1

create table product_type
(
    id   serial not null
        constraint product_type_pk
            primary key,
    name varchar(512)
);

create table product_category
(
    id   serial not null
        constraint product_category_pk
            primary key,
    name varchar(512)
);

create table product
(
    id          serial not null
        constraint product_pk
            primary key,
    type_id     integer
        constraint product_type_id_fk
            references product_type,
    category_id integer
        constraint product_category_id_fk
            references product_category,
    description varchar(512)
);

create table supplier
(
    id      serial not null
        constraint supplier_pk
            primary key,
    name    varchar(512),
    address varchar(2048),
    inn     bigint unique
);

create table supply
(
    id          serial    not null
        constraint supply_pk
            primary key,
    supplier_id integer
        constraint supply_supplier_id_fk
            references supplier,
    created     timestamp not null default now(),
    updated     timestamp
);

create type weight_measure
as enum ('GRAM',
    'KILOGRAM',
    'TON');

create table supply_element
(
    id             serial           not null
        constraint supply_element_pk
            primary key,
    supply_id      integer
        constraint supply_element_supply_id_fk
            references supply       not null,
    product_id     integer
        constraint supply_element_product_id_fk
            references product      not null,
    quantity       integer          not null,
    price          double precision not null,
    weight_measure weight_measure   not null
);

create table product_price
(
    id             serial           not null
        constraint product_price_pk
            primary key,
    product_id     integer
        constraint product_price_product_id_fk
            references product      not null,

    supplier_id    integer
        constraint product_price_supplier_id_fk
            references supplier     not null,
    period_start   date             not null,
    period_end     date             not null,
    price          double precision not null,
    weight_measure weight_measure   not null
);