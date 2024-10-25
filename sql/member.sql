
use Jsp;

create table member
(
    id          VARCHAR(50) primary key,
    password    VARCHAR(100) not null,
    nick_name   varchar(100) not null UNIQUE,
    description VARCHAR(2000),
    inserted    DATETIME     not null default now()
);
#  도부이,    부분함수 종속 , 이행함수 종속 ,

select *
from member;


#권한 테이블
create table auth
(
    id   varchar(50) REFERENCES member (id),
    name varchar(100) not null,
#  한명이 여러 권한일 수 있는걸 가정하에 > 그래서 기본키임
    primary key (id, name)
);

insert into auth(id, name) values
                               ('ad','admin'),
                               ('bd','admin');
select *
from auth;