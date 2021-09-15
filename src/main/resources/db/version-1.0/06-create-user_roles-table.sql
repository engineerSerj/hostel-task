create table if not exists user_roles
(
    user_id bigint,
    role_id bigint,
    primary key (user_id, role_id)
);

alter table user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id)
            references roles;

alter table user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id)
            references users;