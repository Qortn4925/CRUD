

Use Jsp;


create table board(
    id INT PRIMARY KEY auto_increment,
    title varchar(200) not null ,
    content varchar(5000) not null ,
    writer varchar(200) not null ,
    inserted DATETIME not null default  NOW()
);

select  * from board;


INSERT INTO  board
(title, content ,writer)
select title,content,writer
from board;

select*
from board;

# 게시물의 writer 값을 member에 있는 값으로 update
update board
set writer = (select id from member LIMIT 1)
where id > 0;