create table department (department_id bigint not null auto_increment, description varchar(255) not null, primary key (department_id)) engine=InnoDB
create table employee (employee_id bigint not null auto_increment, birth_date date, cpf varchar(14), gender varchar(255), name varchar(255), password varchar(255), register_date datetime(6), telephone varchar(255), email varchar(255), department_id bigint, profile_id bigint, primary key (employee_id)) engine=InnoDB
create table profile (profile_id bigint not null auto_increment, role varchar(255), primary key (profile_id)) engine=InnoDB
alter table employee drop index UK_bvdrevla6o4w5s64n5qoaxsls
alter table employee add constraint UK_bvdrevla6o4w5s64n5qoaxsls unique (cpf)
alter table employee drop index UK_fopic1oh5oln2khj8eat6ino0
alter table employee add constraint UK_fopic1oh5oln2khj8eat6ino0 unique (email)
alter table employee add constraint FKbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department (department_id)
alter table employee add constraint FKksy9iy059ueim17q74yx728ad foreign key (profile_id) references profile (profile_id)