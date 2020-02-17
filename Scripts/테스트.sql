select user(), database ();
select * from title;

select * from employee;

select * from department;


select no, titlename from title;
select no, titlename from title where no=1;

update title set titlename = '사원'where no = 5;

delete from title where no=6;

select deptno, deptname, floor from department;

select deptno, deptname, floor from department where deptno = 3;

update department set deptname ='김장', floor=11 where deptno = 5;

delete 

select no from title order by no desc limit 1;
from department 
where deptno = 5;

select * from employee;

select empno, empname, title, salary, gender, hiredate, dno from employee;

select empno, empname, title, salary, gender, hiredate, dno from employee where empno = 1003;

insert into employee values (1004, '이유영', 2, 2000000, 1,'2010-11-01' ,2);

update employee set empname = '수지', title= 3 ,salary = 2500000, gender=0,hiredate ='2005-05-01',dno=3 where empno =1004;

delete
from employee 
where empno = 1004;

select * from title;

select * from employee;



select * from department;

select e.empno, e.empname, t.no, t.titlename, e.salary, e.gender, d.deptno, d.deptname, d.floor, e.hiredate from employee e left join title t on e.title = t.no left join department d on e.dno = d.deptno;
select empno,hiredate from employee order by empno desc limit 1;

select deptno from department order by deptno desc limit 1;