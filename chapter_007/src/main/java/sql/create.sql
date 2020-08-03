create database tracker;

\connect tracker

create table if not exists roles (
    id serial primary key,
    name varchar(255)
);

create table if not exists users (
   id serial primary key,
   name varchar(255),
   role_id int references roles(id)
);

create table if not exists rules (
     id serial primary key,
     name varchar(255)
);

create table if not exists role_rule (
   id serial primary key,
   role_id int references roles(id),
   rule_id int references rules(id)
);

create table if not exists category (
     id serial primary key,
     name varchar(255)
);

create table if not exists state (
     id serial primary key,
     name varchar(255)
);

create table if not exists items (
     id serial primary key,
     name varchar(255),
	 user_id int references users(id),
	 category_id int references category(id),
	 state_id int references state(id)
);

create table if not exists comments (
     id serial primary key,
     description text,
	 item_id int references items(id)
);

create table if not exists attachs (
     id serial primary key,
     description text,
	 item_id int references items(id)
);

insert into roles (name) values ('Admin');
insert into roles (name) values ('User');

insert into rules (name) values ('Full access');
insert into rules (name) values ('Read only');

insert into users (name, role_id) values ('user 1', 1);
insert into users (name, role_id) values ('user 2', 2);
insert into users (name, role_id) values ('user 3', 2);

insert into state (name) values ('New');
insert into state (name) values ('In the work');
insert into state (name) values ('Closed');