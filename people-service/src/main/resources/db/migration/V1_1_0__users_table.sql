create sequence seq_user;

create table users
(
    id         bigint not null primary key default nextval('seq_user'),
    login      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    birth_date date,
    email      varchar(255)
);
