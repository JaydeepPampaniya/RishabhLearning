create database springSecu1WorkWithDatabase;
use springSecu1WorkWithDatabase;
create table user (
 id int primary key auto_increment,
 userName VARCHAR(1000),
 password varchar(1000)
);

alter table user rename column user_name to username;
insert into user(username,password) values("jaydeep","Jaydeep@77");
insert into user(username,password) values("aashish","Aashish@77");

select * from user;