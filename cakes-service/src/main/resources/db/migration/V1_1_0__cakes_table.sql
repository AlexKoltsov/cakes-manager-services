create sequence seq_cake;

create table cakes
(
    id   bigint not null primary key default nextval('seq_cake'),
    name varchar(255)
);