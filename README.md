sql建表操作
-- auto-generated definition
create table student
(
    id          int auto_increment
        primary key,
    name        varchar(20)                        not null,
    gender      char                               not null,
    age         tinyint                            not null,
    student_no  varchar(10)                        not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    constraint student_no
        unique (student_no)
)
    comment '学生表';

-- auto-generated definition
create table score
(
    id         int auto_increment
        primary key,
    student_id int           not null,
    subject    varchar(50)   not null,
    score      decimal(5, 2) not null,
    exam_time  date          not null
)
    comment '成绩表';
