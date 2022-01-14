create database library2;

use library2;

create table author(
id int auto_increment not null,
name varchar(30),
lastName varchar(30),
primary key (id)
);

create table book(
id int auto_increment not null,
name varchar(30),
pages int not null,
primary key (id)
);

create table author_book(
author_id int not null,
book_id int not null,
primary key (author_id, book_id),
foreign key (author_id) references author(id),
foreign key (book_id) references book(id)
)

