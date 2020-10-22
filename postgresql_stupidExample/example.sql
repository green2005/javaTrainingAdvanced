--create tables
create table departments(
	id int GENERATED ALWAYS AS IDENTITY,
	depName varchar(255),
	primary key(id)
) ;
create table workers
(
	id int GENERATED ALWAYS AS IDENTITY,
	idDep int,
	workerName varchar(255),
	salary real,
	primary key(id),
	CONSTRAINT fk_dep
      FOREIGN KEY(idDep) 
	  REFERENCES departments(id)
);
--fill test data
insert into departments(depName) values('Grodno Azot'),('Dept 1'),('Dept 2'), ('Managers dept')  ;

insert into workers(idDep, workerName, salary)
select 
  d.id,
  'Иванов',
  120.0
from departments d
where d.depName = 'Dept 1';


insert into workers(idDep, workerName, salary)
select 
  d.id,
  'Петров',
  140.0
from departments d
where d.depName = 'Dept 2';


insert into workers(idDep, workerName, salary)
select 
  d.id,
  'Абрамович',
  1000000.0
from departments d
where d.depName = 'Managers dept';


Select avg(salary) from workers where workerName<>'Абрамович'