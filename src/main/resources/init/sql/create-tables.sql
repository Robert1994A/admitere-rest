create table if not exists countries (
   id bigint not null,
    creation_date datetime,
    name varchar(255),
    phonecode varchar(255),
    sortname varchar(255),
    primary key (id)
) engine=InnoDB;


create table if not exists states (
   id bigint not null,
    creation_date datetime,
    name varchar(255),
    country_id bigint,
    primary key (id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
) engine=InnoDB;


create table if not exists cities (
   	id bigint not null,
    creation_date datetime,
    name varchar(255),
    state_id bigint,
    primary key (id),
    FOREIGN KEY (state_id) REFERENCES states(id)
) engine=InnoDB;