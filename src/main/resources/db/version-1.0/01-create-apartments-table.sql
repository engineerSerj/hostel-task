create table if not exists apartments
(
    id               integer generated by default as identity,
    apartment_number integer not null,
    cleaning_date    timestamp,
    room_amount      integer not null,
    apartment_id     integer,
    primary key (id)
);
