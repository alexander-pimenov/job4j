--Хранилище машин[1733#210760]
--Нужно написать SQL скрипты:
--Создать структур данных в базе.
--Таблицы.
--   Кузов. Двигатель, Коробка передач.
--Создать структуру Машина. Машина не может существовать без данных из п.1.
--Заполнить таблицы через insert.
--Создать SQL запросы:
--1. Вывести список всех машин и все привязанные к ним детали.
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--/////////////////////////////////////////////////////////////////////////////////////
--//Создание базы данных car_catalog

--//запускаем командную строку и сначала устанавливаем кодировку кириллицы:

--chcp 1251

--//в консоли подключаемся к БД postgres

--psql --username=postgres

--//устанавливаем кодировку для кириллицы в postgres

--set client_encoding='win1251';

--//можем проверить все имеющиеся Базы Данных

--\l

--//Создаем  новую БД car_catalog

--create database car_catalog;

-- Database: car_catalog скрипт из pgAdmin

-- DROP DATABASE car_catalog;

CREATE DATABASE car_catalog
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

--//подключаемся к этой БД

--\c car_catalog

--//создаем таблицы
create table if not exists body (
     id serial primary key,
     name varchar(255)
);

create table if not exists engine (
     id serial primary key,
     name varchar(255)
);

create table if not exists transmission (
     id serial primary key,
     name varchar(255)
);

create table if not exists car (
     id serial primary key,
     name varchar(255),
	 body_id int references body(id),
	 engine_id int references engine(id),
	 transmission_id int references transmission(id)
);

--заполняем таблицы данными
insert into body (name) values ('Hatchback');
insert into body (name) values ('Sedan');
insert into body (name) values ('Wagon');
insert into body (name) values ('Jeep');
insert into body (name) values ('Coupe');

insert into engine (name) values ('Diesel');
insert into engine (name) values ('Petrol');
insert into engine (name) values ('Hybrid');
insert into engine (name) values ('Electric');

insert into transmission (name) values ('Automatic transmission');
insert into transmission (name) values ('Manual transmission');
insert into transmission (name) values ('Continuously variable transmission');
insert into transmission (name) values ('Semi-automatic transmissions');

insert into car (name, body_id, engine_id, transmission_id) values ('BMV',2,2,1);
insert into car (name, body_id, engine_id, transmission_id) values ('Toyota',1,3,3);
insert into car (name, body_id, engine_id, transmission_id) values ('Nissan',5,4,1);
insert into car (name, body_id, engine_id, transmission_id) values ('Audi',5,2,3);
insert into car (name, body_id, engine_id, transmission_id) values ('Mazda',3,2,4);
insert into car (name, body_id, engine_id, transmission_id) values ('Mitsubishi',4,1,2);

--Добавим не полные записи в таблицу car:

 insert into car(name, body_id, transmission_id ) values ('Car7', 1, 4);
 insert into car(name, transmission_id) values ('Car8', 1);
 insert into car(name, body_id, engine_id) values ('Car9', 3, 3);
 insert into car(name, engine_id, transmission_id) values ('Car10', 2, 2);
 insert into car(name) values ('Car11');

--1. Вывести список всех машин и все привязанные к ним детали.
-- вывод всех колонок после соединения.
--//Правильное соединение будет через inner join, т.к. машина не может
-- существовать без деталей (без кузова, без трансмиссии, без двигателя).
-- Те машины которые записаны в таблицу без некоторых деталей,
-- в этом результате не появятся.

select c.name, b.name as body_car, e.name as engine_car,
 	t.name as transmission_car
		from car c
			inner join body b on c.body_id=b.id
			inner join engine e on c.engine_id=e.id
			inner join transmission t on c.transmission_id=t.id;

--2. Вывести отдельно детали, которые не используются в машине,
--   кузова, двигатели, коробки передач.

select c.name, t.name as transmission_car
	from car as c left join transmission as t on t.id = c.transmission_id
		where c.transmission_id is null;

select c.name, b.name as body_car
from car as c left join body as b on b.id = c.body_id
	where c.body_id is null;

select c.name, e.name as engine_car
from car as c left join engine as e on e.id = c.engine_id
	where c.engine_id is null;
