
    create table wp_commentmeta (
        comment_id bigint,
        meta_id bigint not null auto_increment,
        meta_key varchar(255),
        meta_value longtext,
        primary key (meta_id)
    ) engine=InnoDB;

    create table wp_comments (
        comment_karma integer not null,
        comment_date datetime(6),
        comment_date_gmt datetime(6),
        comment_id bigint not null auto_increment,
        comment_parent bigint,
        user_id bigint,
        comment_approved varchar(20) not null,
        comment_type varchar(20) not null,
        comment_author_email varchar(100) not null,
        comment_author_ip varchar(100) not null,
        comment_author_url varchar(200) not null,
        comment_agent varchar(255) not null,
        comment_author varchar(255) not null,
        comment_content varchar(255) not null,
        primary key (comment_id)
    ) engine=InnoDB;

    create table wp_links (
        link_rating integer not null,
        link_id bigint not null auto_increment,
        link_owner bigint not null,
        link_updated datetime(6),
        link_visible varchar(20) not null,
        link_target varchar(25) not null,
        link_description varchar(255) not null,
        link_image varchar(255) not null,
        link_name varchar(255) not null,
        link_rel varchar(255) not null,
        link_rss varchar(255) not null,
        link_url varchar(255) not null,
        link_notes mediumtext not null,
        primary key (link_id)
    ) engine=InnoDB;

    create table wp_options (
        option_id bigint not null auto_increment,
        autoload varchar(20) not null,
        option_name varchar(255) not null,
        option_value longtext not null,
        primary key (option_id)
    ) engine=InnoDB;

    create table wp_postmeta (
        meta_id bigint not null auto_increment,
        post_id bigint,
        meta_key varchar(255),
        meta_value longtext,
        primary key (meta_id)
    ) engine=InnoDB;

    create table wp_posts (
        menu_order integer,
        comment_count bigint,
        id bigint not null auto_increment,
        post_author bigint,
        post_date datetime(6),
        post_date_gmt datetime(6),
        post_modified datetime(6),
        post_modified_gmt datetime(6),
        post_parent bigint,
        comment_status varchar(20) not null,
        ping_status varchar(20) not null,
        post_status varchar(20) not null,
        post_type varchar(20) not null,
        post_mime_type varchar(100) not null,
        post_name varchar(200) not null,
        guid varchar(255) not null,
        post_password varchar(255) not null,
        pinged text not null,
        post_content longtext not null,
        post_content_filtered longtext not null,
        post_excerpt text not null,
        post_title text not null,
        to_ping text not null,
        primary key (id)
    ) engine=InnoDB;

    create table wp_term_relationships (
        term_order integer,
        object_id bigint not null auto_increment,
        term_taxonomy_id bigint,
        primary key (object_id)
    ) engine=InnoDB;

    create table wp_term_taxonomy (
        count bigint,
        parent bigint,
        term_id bigint,
        term_taxonomy_id bigint not null auto_increment,
        taxonomy varchar(32) not null,
        description longtext not null,
        primary key (term_taxonomy_id)
    ) engine=InnoDB;

    create table wp_termmeta (
        meta_id bigint not null auto_increment,
        term_id bigint,
        meta_key varchar(255),
        meta_value longtext,
        primary key (meta_id)
    ) engine=InnoDB;

    create table wp_terms (
        term_group bigint not null,
        term_id bigint not null auto_increment,
        name varchar(200) not null,
        slug varchar(200) not null,
        primary key (term_id)
    ) engine=InnoDB;

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

    create index comment_id 
       on wp_commentmeta (comment_id);

    create index meta_key 
       on wp_commentmeta (meta_key);

    create index comment_post_ID 
       on wp_comments (comment_post_id);

    create index comment_approved_date_gmt 
       on wp_comments (comment_approved, comment_approved_date_gmt);

    create index comment_parent 
       on wp_comments (comment_parent);

    create index comment_author_email 
       on wp_comments (comment_author_email);

    create index user_login_key 
       on wp_users (user_login);

    create index user_nicename 
       on wp_users (user_nicename);

    create index user_email 
       on wp_users (user_email);

    alter table wp_commentmeta 
       add constraint FKfg27hvnsobyswnxhulygko4pm 
       foreign key (comment_id) 
       references wp_comments (comment_id);

    alter table wp_comments 
       add constraint FKpmnya3wwwpfegi9evk0ds3wct 
       foreign key (comment_parent) 
       references wp_comments (comment_id);

    alter table wp_comments 
       add constraint FKpvlitmkfab9ydob6n2notnkes 
       foreign key (user_id) 
       references wp_users (id);

    alter table wp_links 
       add constraint FKbn93e8612f9eyveo030eac86c 
       foreign key (link_owner) 
       references wp_users (id);

    alter table wp_postmeta 
       add constraint FK1o2i0nnygsy6j86khsaxesjht 
       foreign key (post_id) 
       references wp_posts (id);

    alter table wp_posts 
       add constraint FKmotxlhwx9etjjls34ksvvx20x 
       foreign key (post_author) 
       references wp_users (id);

    alter table wp_posts 
       add constraint FK6l9y9u6ndsy6k3dg75l1qxak9 
       foreign key (post_parent) 
       references wp_posts (id);

    alter table wp_term_relationships 
       add constraint FKjbtcivmj38rl6by2i458773rm 
       foreign key (term_taxonomy_id) 
       references wp_term_taxonomy (term_taxonomy_id);

    alter table wp_term_taxonomy 
       add constraint FK3mnr31pbnlcuywirhjbhyao1o 
       foreign key (parent) 
       references wp_term_taxonomy (term_taxonomy_id);

    alter table wp_term_taxonomy 
       add constraint FKpiqwakn9cd55m7nw2e3d51n55 
       foreign key (term_id) 
       references wp_terms (term_id);

    alter table wp_termmeta 
       add constraint FKghqjk408j74aelfpog0kxo9iq 
       foreign key (term_id) 
       references wp_terms (term_id);

    alter table wp_usermeta 
       add constraint FKniuireivesyp22quau4louao1 
       foreign key (user_id) 
       references wp_users (id);
