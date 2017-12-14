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
  username varchar(32) unique,
  password varchar(32) not null,
  score char(5) default 0,
  registrationdate TIMESTAMP default CURRENT_TIMESTAMP
);

commit;
