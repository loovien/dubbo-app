create database if not exists `dubbo-app` charset utf8mb4;

use `dubbo-app`;

-- users table
create table if not exists users (
    id int unsigned not null auto_increment,
    username varchar(64) not null default '' comment '昵称',
    password varchar(255) not null default '' comment '密码',
    created_date datetime not null default current_timestamp comment '创建时间',
    updated_date datetime null default null comment '更新时间',
    primary key(id),
    unique key idx_username(username),
    key idx_created_date (created_date)
) engine innodb charset utf8mb4;

-- message table
create table if not exists messages (
    id int unsigned not null auto_increment,
    sender int unsigned not null default '0' comment 'sender',
    receiver int unsigned not null default '0' comment 'receive',
    content varchar(512) not null default '' comment 'content',
    created_date datetime not null default current_timestamp comment '创建时间',
    primary key(id),
    key idx_from (sender),
    key idx_to(receiver),
    key idx_created_date(created_date)
) engine innodb charset utf8mb4 comment 'message table';