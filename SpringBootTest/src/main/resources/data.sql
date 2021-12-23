insert into user values(9000, sysdate(), 'User1', 'test1', '991231-1111111');
insert into user values(9001, sysdate(), 'User2', 'test1', '001231-1111111');
insert into user values(9002, sysdate(), 'User3', 'test1', '801231-1111111');

insert into post (ID , description, user_id) values(1001, 'first', 9001);
insert into post (ID , description, user_id) values(1002, 'second', 9001);