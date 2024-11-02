create schema api;

create table api."user"
(
    id          bigint
        constraint id primary key generated always as identity,
    created_date date default CURRENT_DATE not null,
    username    text                      not null,
    email       text
);

alter table api."user" owner to pg;

create table api.address (
    id bigint generated always as identity,
    user_id bigint references api."user" (id),
    city text,
    country text
);

alter table api.address owner to pg;

alter table api.address add constraint unique_address_record primary key (id, user_id);
