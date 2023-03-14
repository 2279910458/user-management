create database if not exists `user-demo`;

drop table if exists `user`;

create table if not exists `user`
(
    id      bigint auto_increment not null comment 'id',
    name    varchar(30) default '' comment '姓名',
    age     int(10)     default null comment '年龄',
    gender  tinyint(1)  default null comment '性别：0-男，1-女',
    phone   varchar(15) default null comment '联系电话',
    address varchar(64) default null comment '地址',
    deleted tinyint     default 0 comment '0-未删，1-已删',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

insert into user(id, name, age, gender, phone, address)
values (1, '张三', 18, 0, '12312323232', '英国伦敦'),
       (2, '李四', 20, 1, '19999999999', '美国华盛顿');