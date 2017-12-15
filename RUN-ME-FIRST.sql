/* Save and run the entire script in one file */
/* Student information database tables
   Run this from the root */

drop database if exists holdemDB;
create database if not exists holdemDB;

create user if not exists 'group'@'localhost' identified by 'group';
grant select, insert, update, delete, create, create view, drop,
 execute, references on holdemDB.* to 'group'@'localhost';

use holdemDB;

create table if not exists users (
ID int not null AUTO_INCREMENT,
  username varchar(32) unique not null,
  password varchar(32) not null,
  registrationdate TIMESTAMP default CURRENT_TIMESTAMP,
  primary key(ID)
);

create table if not exists scores (
ID int not null,
  score char(5) default 0,
  chipCount char(5) default 1000,
  primary key (ID)
);

commit;
