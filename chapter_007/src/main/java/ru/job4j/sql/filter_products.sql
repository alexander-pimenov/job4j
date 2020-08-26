-- Создаем таблицы для типа продуктов type и самих продуктов product:

create table if not exists type (
     id serial primary key,
     name varchar(255)
);

create table if not exists product (
     id serial primary key,
     name varchar(255),
     type_id int references type(id),
     expired_date date,
     price money
);

-- Заполняем таблицы некоторыми данными:
insert into type (name) values ('молочные продукты');
insert into type (name) values ('мясные продукты');
insert into type (name) values ('сыр');
insert into type (name) values ('рыбные продукты');
insert into type (name) values ('овощные продукты');
insert into type (name) values ('кондитерские продукты');
insert into type (name) values ('хлебобулочные изделия');

insert into product (name, type_id, expired_date, price) values ('Сметана', 1, '2020-10-20', 50);
insert into product (name, type_id, expired_date, price) values ('Кефир', 1, '2020-09-20', 70);
insert into product (name, type_id, expired_date, price) values ('Вологодское мороженное', 1, '2020-11-20', 65);

insert into product (name, type_id, expired_date, price) values ('Грудинка копченая', 2, '2020-12-20', 400);
insert into product (name, type_id, expired_date, price) values ('Окорок', 2, '2020-09-20', 390);
insert into product (name, type_id, expired_date, price) values ('Лопатка', 2, '2020-09-10', 370);

insert into product (name, type_id, expired_date, price) values ('Российский', 3, '2020-12-10', 500);
insert into product (name, type_id, expired_date, price) values ('Голландский', 3, '2021-01-10', 450);
insert into product (name, type_id, expired_date, price) values ('Эдам', 3, '2020-11-10', 600);
insert into product (name, type_id, expired_date, price) values ('Пошехонский', 3, '2020-12-10', 450);
insert into product (name, type_id, expired_date, price) values ('Сулугуни', 3, '2021-02-10', 520);
insert into product (name, type_id, expired_date, price) values ('Маасдам', 3, '2020-11-20', 660);

insert into product (name, type_id, expired_date, price) values ('Сельдь атлантическая', 4, '2020-10-10', 250);
insert into product (name, type_id, expired_date, price) values ('Скумбрия', 4, '2020-11-10', 330);
insert into product (name, type_id, expired_date, price) values ('Горбуша', 4, '2020-11-10', 370);

insert into product (name, type_id, expired_date, price) values ('Огурцы', 5, '2020-09-10', 70);
insert into product (name, type_id, expired_date, price) values ('Помидоры', 5, '2020-09-20', 90);
insert into product (name, type_id, expired_date, price) values ('Капуста', 5, '2020-10-20', 35);
insert into product (name, type_id, expired_date, price) values ('Кабачки', 5, '2020-10-20', 48);
insert into product (name, type_id, expired_date, price) values ('Баклажаны', 5, '2020-10-20', 85);

insert into product (name, type_id, expired_date, price) values ('Сникерс', 6, '2021-02-20', 45);
insert into product (name, type_id, expired_date, price) values ('Вафли шоколадные', 6, '2020-12-20', 55);

insert into product (name, type_id, expired_date, price) values ('Батон нарезной', 7, '2020-09-20', 25);
insert into product (name, type_id, expired_date, price) values ('Хлеб крестьянский', 7, '2020-10-20', 55);
insert into product (name, type_id, expired_date, price) values ('Багет', 7, '2020-09-15', 35);

--Задание.
--1. Написать запрос получение всех продуктов с типом "сыр":

--Соединение таблиц LEFT JOIN
--select p.name, t.name as type_name
--	from product as p
--	left join type as t
--	on p.type_id=t.id;

select p.name, t.name as type_name, p.price
	from product as p
	left join type as t
	on p.type_id=t.id
	where t.name='сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть
--   слово "мороженное":

select p.name, t.name as type_name, p.price, p.expired_date
	from product as p
	left join type as t
	on p.type_id=t.id
	where p.name like '%мороженное%';

--или только для таблицы продукты:

select * from product where name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности
--   которых заканчивается в следующем месяце:

select name from product
where extract(month from expired_date) = extract(month from now() + interval '1 month');

--или:

select p.name as product_name,
	t.name as type_name,
	p.price, p.expired_date
	from product as p
	left join type as t
	on p.type_id=t.id
	where
	extract(month from p.expired_date) = extract(month from now() + interval '1 month');

--4. Написать запрос, который выводит самый дорогой продукт.

select p.name as product_name, t.name as type_name, max(p.price)
from product as p
left join type as t on p.type_id=t.id
where p.price =
   (select max(price) from product)
group by p.name, t.name;

--5. Написать запрос, который выводит количество всех продуктов
--определенного типа.
--вывод по определенному типу:

select count(*) as "молочные продукты" from product as p left join type as t on p.type_id  = t.id where t.name = 'молочные продукты';
select count(*) as "сыр" from product as p left join type as t on p.type_id  = t.id where t.name = 'сыр';
select count(*) as "рыбные продукты" from product as p left join type as t on p.type_id  = t.id where t.name = 'рыбные продукты';
select count(*) as "овощные продукты" from product as p left join type as t on p.type_id  = t.id where t.name = 'овощные продукты';

--или все типы с количеством продуктов сразу:

select t.name as type_name, count(*)
from product as p left join type as t on p.type_id=t.id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "сыр" и "молочные продукты" ("СЫР" и "МОЛОКО")

select p.name, t.name as type_name, p.price, p.expired_date
	from product as p
		left join type as t on p.type_id=t.id
where t.name = 'сыр' or t.name = 'молочные продукты';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 4 (10) штук.

select t.name as type_name, count(*)
	from product as p
	left join type as t
		on p.type_id=t.id
		group by t.name
		having count(*)<4;

--8. Вывести все продукты и их тип.

select p.name as "название продукта", t.name as "тип продукта"
	from product as p
	left join type as t
	on p.type_id=t.id
	order by t.name;

