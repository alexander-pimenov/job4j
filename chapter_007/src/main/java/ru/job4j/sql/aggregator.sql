--Задание:
--Создайте sql schema для проекта агрегатор (aggregator).
--В базе должна быть таблица post (id, name, text, link, created)

--id - первичный ключ
--name - имя вакансии
--text - текст вакансии - (изменил на description)
--link - текст, ссылка на вакансию
--created - дата первого поста.
--updated - дата изменения поста (добавил самостоятельно)

--Нужно учесть дубликаты. Какой поле будет уникальным кроме id?
--1. Создайте схему post.


--Решение:
--создаем базу данных aggregator
create database aggregator;

--подключаемся БД aggregator
\connect aggregator

--создаем новую схему post в БД aggregator
create schema post;

--переключаемся из схемы public в схему post
set search_path to post;

--создаем таблицу post в схеме post
--чтобы не было дубликатов, проверяем уникальность каждой ссылки link
create table if not exists post (
    id serial primary key,
    name varchar(255) not null,
	description text,
	link text unique not null,
	created timestamp(0) without time zone default now() not null,
	updated timestamp(0) without time zone
);

--заполним таблицу первичными данными
insert into post (name, description, link) values ('Java-developer', 'Зп 150 т.руб., удаленно', 'http://rabota.tu/101025');
insert into post (name, description, link) values ('JS-developer', 'Зп 120 т.руб., Москва', 'http://job.su/95951025');
insert into post (name, description, link) values ('C#-developer', 'Зп 130 т.руб., Казань', 'http://worki.nu/4875962');
--изменим дату updated поста JS-developer
update post set updated='2020-10-10 15:14:13' where id=2;
--если указать дату так, то чч:мм:сс будут в нулях
--update post set updated='2020-10-10' where id=2;