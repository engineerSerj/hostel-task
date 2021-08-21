create table if not exists guests
(
    id              bigint generated by default as identity,
    birthday        date,
    check_in_date   timestamp,
    check_out_date  timestamp,
    file_name_photo varchar(255),
    full_name       varchar(255),
    passport        varchar(255),
    apartment_id    integer,
    primary key (id)
);

alter table guests
    add constraint FKpljc5pnqdqt5ujk3vduj7gvie
        foreign key (apartment_id)
            references apartments;
