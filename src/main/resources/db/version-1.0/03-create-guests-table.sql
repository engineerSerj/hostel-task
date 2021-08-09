create table guests (
                        id integer generated by default as identity,
                        birthday timestamp,
                        checkInDate timestamp,
                        checkOutDate timestamp,
                        fileNamePhoto varchar(255),
                        fullName varchar(255),
                        passport varchar(255),
                        guest_id integer,
                        primary key (id)
);
