create table projects
(
    project_id   bigserial    not null
        constraint projects_pkey
            primary key,
    finish_date  date,
    project_name varchar(255) not null
        constraint uk_5brqsoho9qc97d54l39n7osde
            unique,
    start_date   date
);

alter table projects
    owner to postgres;

create table employees
(
    employee_id bigserial    not null
        constraint employees_pkey
            primary key,
    birth_date  date         not null,
    email       varchar(255) not null
        constraint uk_j9xgmd0ya5jmus09o0b8pqrpb
            unique,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    phone       varchar(255) not null
        constraint uk_gnponadwwxr5nm2tqe5b905hs
            unique,
    username    varchar(255) not null
        constraint uk_3gqbimdf7fckjbwt1kcud141m
            unique,
    project_id  bigint
        constraint fkgawtrwvxw4uu0ppt5h8w5go
            references projects
);

alter table employees
    owner to postgres;