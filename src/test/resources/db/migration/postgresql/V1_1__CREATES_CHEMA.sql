create table CryptocurrencyJpaEntity (
       id  serial not null,
        Symbol varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table RoleJpaEntity (
       id  serial not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table USER_ROLES (
       USER_ID int4 not null,
        ROLE_ID int4 not null,
        primary key (USER_ID, ROLE_ID)
    );

    create table UserJpaEntity (
       id  serial not null,
        active boolean,
        password varchar(255),
        username varchar(255),
        primary key (id)
    );

    alter table USER_ROLES
       add constraint FKgb2khn315n46afwqm23bhkpmy
       foreign key (ROLE_ID)
       references RoleJpaEntity
;

    alter table USER_ROLES
       add constraint FK1nhfxl0hhdg2bum7yc75j783j
       foreign key (USER_ID)
       references UserJpaEntity
       ;

