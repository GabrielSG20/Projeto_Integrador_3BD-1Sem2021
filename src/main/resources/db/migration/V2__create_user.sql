use codelist;

create user 'admin'@'localhost' identified by 'admin';

grant select, insert, delete, update on codelist.* to admin@'localhost';
