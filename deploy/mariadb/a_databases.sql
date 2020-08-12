CREATE DATABASE IF NOT EXISTS student;

create table if not exists student.student
(
    id         INT auto_increment primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) null
);

INSERT INTO student.student (first_name, last_name) VALUES ('updated_first_name', 'test');
