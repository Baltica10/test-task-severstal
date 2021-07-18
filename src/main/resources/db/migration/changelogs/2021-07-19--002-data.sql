--liquibase formatted sql

--changeset an.krasnov:2

insert into product_type(name)
values ('Груша'),
       ('Яблоко');

insert into product_category(name)
values ('Новый урожай'),
       ('Дачный сорт'),
       ('Скороспелка'),
       ('Конференция');

insert into product(type_id, category_id, description)
values (1, 1, 'топ-100 товаров'),
       (1, 2, 'с гнильцой'),
       (2, 3, 'фасованные'),
       (2, 4, 'есть червивые');

insert into supplier(name, address, inn)
values ('Поставщик № 1', 'Москва', 123456789101),
       ('Поставщик № 2', 'Орел', 123456789102),
       ('Поставщик № 3', 'Клин', 123456789103);

insert into product_price(supplier_id, product_id, period_start, period_end, price, weight_measure)
values (1, 1, '2021-01-01', '2021-12-31', 100, 'KILOGRAM'),
       (1, 2, '2021-01-01', '2021-12-31', 110, 'KILOGRAM'),
       (1, 3, '2021-01-01', '2021-12-31', 120, 'KILOGRAM'),
       (1, 4, '2021-01-01', '2021-12-31', 130, 'KILOGRAM'),
       (2, 1, '2021-01-01', '2021-12-31', 140, 'KILOGRAM'),
       (2, 2, '2021-01-01', '2021-12-31', 150, 'KILOGRAM'),
       (2, 3, '2021-01-01', '2021-12-31', 160, 'KILOGRAM'),
       (2, 4, '2021-01-01', '2021-12-31', 170, 'KILOGRAM'),
       (3, 1, '2021-01-01', '2021-12-31', 180, 'KILOGRAM'),
       (3, 2, '2021-01-01', '2021-12-31', 190, 'KILOGRAM'),
       (3, 3, '2021-01-01', '2021-12-31', 200, 'KILOGRAM'),
       (3, 4, '2021-01-01', '2021-12-31', 210, 'KILOGRAM');