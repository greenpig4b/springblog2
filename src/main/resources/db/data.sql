
-- 유저
insert into user_tb(user_name,password,email,created_at) values('ssar','1234','ssar@nate.com',now());
insert into user_tb(user_name,password,email,created_at) values('cos','1234','cos@nate.com',now());
insert into user_tb(user_name,password,email,created_at) values('love','1234','love@nate.com',now());


-- 테이블
insert into board_tb(user_id,title,content,created_at,me) values(1,'제목1','내용1',now(),false);
insert into board_tb(user_id,title,content,created_at,me) values(1,'제목2','내용2',now(),false);
insert into board_tb(user_id,title,content,created_at,me) values(2,'제목3','내용3',now(),false);
insert into board_tb(user_id,title,content,created_at,me) values(3,'제목4','내용4',now(),false);

