    create table wp_usermeta (
        umeta_id bigint not null auto_increment,
        user_id bigint,
        meta_key varchar(255),
        meta_value longtext,
        primary key (umeta_id)
    ) engine=InnoDB;

    create table wp_users (
        user_status integer,
        id bigint not null auto_increment,
        user_registered datetime(6),
        user_nicename varchar(50) not null,
        user_login varchar(60) not null,
        user_email varchar(100) not null,
        user_url varchar(100) not null,
        display_name varchar(255) not null,
        user_activation_key varchar(255) not null,
        user_pass varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create index user_login_key 
       on wp_users (user_login);

    create index user_nicename 
       on wp_users (user_nicename);

    create index user_email 
       on wp_users (user_email);

    alter table wp_usermeta 
       add constraint FKniuireivesyp22quau4louao1 
       foreign key (user_id) 
       references wp_users (id);
