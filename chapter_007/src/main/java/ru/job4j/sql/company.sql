--Создаем две таблицы company и person.
--id в компании задаем вручную.
create table company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

create table person
(
    id serial NOT NULL,
    name character varying,
    company_id integer REFERENCES company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

--Заполним таблицы данными:
 insert into company(id, name) values (1, 'Alfa'),
 			(2, 'Beta'), (3, 'Gamma'), (4, 'Delta'),
 			(5, 'Omega'), (6, 'Epsilon'), (7, 'Simbolos');

 insert into person(name, company_id) values ('Mark', 1),
  		    ('Alex', 2), ('Ivan', 2), ('Timati', 2), ('Zack', 2),
  		    ('Fox', 3), ('Anton', 3), ('Wolf', 3), ('Petr', 4),
 			('John', 5), ('Sam', 5), ('Bob', 6);

--Выведем все данные двух таблиц:

select p.name, p.company_id, c.id, c.name from person as p
    inner join company as c on c.id=p.company_id;

-- нужно:
-- 1) Получить одним запросом:
-- - имена всех людей, которые не в компании с id=5
-- - название компании для каждого человека

select p.name, c.name from person as p
    join company as c on c.id=p.company_id where c.id <> 5;


-- 2) Выберите название компании с максимальным количеством
--	 человек + количество человек в этой компании

select
 	c.name,
 	count (p.id) amount_of_people
from
 	company as c
    inner join person as p on c.id=p.company_id
group by
 	c.name
order by amount_of_people desc;

