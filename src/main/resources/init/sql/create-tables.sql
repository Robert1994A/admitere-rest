create table if not exists countries (
    id integer not null AUTO_INCREMENT,
    creation_date datetime,
    name varchar(255),
    phone_code varchar(255),
    sort_name varchar(255),
    primary key (id)
) engine=InnoDB;


create table if not exists states (
   id integer not null AUTO_INCREMENT,
    creation_date datetime,
    name varchar(255),
    country_id integer,
    primary key (id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
) engine=InnoDB;


create table if not exists cities (
   	id integer not null AUTO_INCREMENT,
    creation_date datetime,
    name varchar(255),
    state_id integer,
    primary key (id),
    FOREIGN KEY (state_id) REFERENCES states(id)
) engine=InnoDB;