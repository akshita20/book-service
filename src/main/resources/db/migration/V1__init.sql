create table books (
id integer NOT NULL AUTO_INCREMENT,
author_name varchar(255) not null,
created_at timestamp not null,
book_name varchar(255) not null,
updated_at timestamp,
primary key (id));