create table hibernate_sequence
(
    next_val bigint null
);

create table t_essay
(
    id          bigint         not null
        primary key,
    color       varchar(255)   null,
    content     varchar(10000) null,
    create_time datetime(6)    null,
    praise      bigint         null,
    title       varchar(255)   null
);

create table t_message
(
    id          bigint       not null
        primary key,
    avatar      varchar(255) null,
    content     varchar(255) null,
    create_time datetime(6)  null,
    nickname    varchar(255) null,
    user_id     bigint       null
);

create table t_picture
(
    id               bigint auto_increment
        primary key,
    dialog_image_url varchar(255) null
);

create table picture_picture_list
(
    picture_id   bigint       not null,
    picture_list varchar(255) null,
    constraint FKtw9ftnrv6yeufvboliecmw8
        foreign key (picture_id) references t_picture (id)
);

create table t_project
(
    id      bigint       not null
        primary key,
    content varchar(255) null,
    pic_url varchar(255) null,
    techs   varchar(255) null,
    title   varchar(255) null,
    type    int          null,
    url     varchar(255) null
);

create table t_tag
(
    id   bigint       not null
        primary key,
    name varchar(255) null
);

create table t_type
(
    id      bigint       not null
        primary key,
    color   varchar(255) null,
    name    varchar(255) null,
    pic_url varchar(255) null
);

create table t_user
(
    id              bigint       not null
        primary key,
    avatar          varchar(255) null,
    create_time     datetime(6)  null,
    email           varchar(255) null,
    last_login_time datetime(6)  null,
    login_city      varchar(255) null,
    login_lat       varchar(255) null,
    login_lng       varchar(255) null,
    login_province  varchar(255) null,
    nickname        varchar(255) null,
    password        varchar(255) null,
    type            varchar(255) null,
    update_time     datetime(6)  null,
    username        varchar(255) null
);

create table t_blog
(
    id              bigint                    not null
        primary key,
    appreciation    int                       null,
    commentabled    bit                       not null,
    content         varchar(10000)            null,
    create_time     datetime(6)               null,
    description     varchar(255) charset utf8 null,
    first_picture   varchar(255) charset utf8 null,
    flag            varchar(255) charset utf8 null,
    published       bit                       not null,
    recommend       bit                       not null,
    share_statement bit                       not null,
    title           varchar(255) charset utf8 null,
    update_time     datetime(6)               null,
    views           int                       null,
    type_id         bigint                    null,
    user_id         bigint                    null,
    constraint FK292449gwg5yf7ocdlmswv9w4j
        foreign key (type_id) references t_type (id),
    constraint FK8ky5rrsxh01nkhctmo7d48p82
        foreign key (user_id) references t_user (id)
)
    collate = utf8mb4_unicode_ci;

create table t_blog_tags
(
    blogs_id bigint not null,
    tags_id  bigint not null,
    constraint FK5feau0gb4lq47fdb03uboswm8
        foreign key (tags_id) references t_tag (id),
    constraint FKh4pacwjwofrugxa9hpwaxg6mr
        foreign key (blogs_id) references t_blog (id)
);

create table t_comment
(
    id                bigint       not null
        primary key,
    admin_comment     bit          not null,
    avatar            varchar(255) null,
    content           varchar(255) null,
    create_time       datetime(6)  null,
    email             varchar(255) null,
    nickname          varchar(255) null,
    user_id           bigint       null,
    blog_id           bigint       null,
    parent_comment_id bigint       null,
    constraint FK4jj284r3pb7japogvo6h72q95
        foreign key (parent_comment_id) references t_comment (id),
    constraint FKke3uogd04j4jx316m1p51e05u
        foreign key (blog_id) references t_blog (id)
);

