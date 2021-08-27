create table refreshtoken
(
    id          bigint not null,
    expiry_date timestamp,
    token       varchar(255),
    user_id     bigint,
    primary key (id)
);
alter table refreshtoken
    add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token);

alter table refreshtoken
    add constraint FKa652xrdji49m4isx38pp4p80p foreign key (user_id) references users;