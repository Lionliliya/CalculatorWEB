CREATE DATABASE calculatorDB CHARACTER SET utf8 COLLATE utf8_general_ci;

use calculatorDB;

create table CALCULATOR (
	id int not null auto_increment primary key,
    EXPR varchar(200) not null,
    RESULT DECIMAL(10, 3) not null,
    ERROR varchar(10) not null     
);

alter table CALCULATOR add user_id bigint;

create table USER (
	id int not null auto_increment primary key,
    USERNAME varchar(120) not null,
    PASSWORD varchar(50) not null
);
