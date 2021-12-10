create table CryptocurrencyJpaEntity (
       id  serial not null,
        Symbol varchar(255),
        name varchar(255),
        primary key (id)
    );


    create table ExchangeCompanyJpaEntity (
       id  serial not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );


    create table OHLCValueJpaEntity (
       id  serial not null,
        Low float8,
        close float8,
        date timestamp,
        high float8,
        open float8,
        unixTimestamp int8,
        volume float8,
        cryptocurrencyJpaEntity_id int4,
        exchangeCompanyJpaEntity_id int4,
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


    alter table CryptocurrencyJpaEntity
       drop constraint if exists UK_i1tjiarmf952hfsvl8g431q7l;

    alter table CryptocurrencyJpaEntity
       add constraint UK_i1tjiarmf952hfsvl8g431q7l unique (name);


    alter table ExchangeCompanyJpaEntity
       drop constraint if exists UK_aobid1g9tt902owc1ktda5pd9;

    alter table ExchangeCompanyJpaEntity
       add constraint UK_aobid1g9tt902owc1ktda5pd9 unique (name);


    alter table OHLCValueJpaEntity
       add constraint FK85652x8xo2sy2wx2do56hkd81
       foreign key (cryptocurrencyJpaEntity_id)
       references CryptocurrencyJpaEntity;


    alter table OHLCValueJpaEntity
       add constraint FK1a3p71c2386s8jwe3wcd545uq
       foreign key (exchangeCompanyJpaEntity_id)
       references ExchangeCompanyJpaEntity;


    alter table USER_ROLES
       add constraint FKgb2khn315n46afwqm23bhkpmy
       foreign key (ROLE_ID)
       references RoleJpaEntity;


    alter table USER_ROLES
       add constraint FK1nhfxl0hhdg2bum7yc75j783j
       foreign key (USER_ID)
       references UserJpaEntity;
