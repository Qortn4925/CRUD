

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