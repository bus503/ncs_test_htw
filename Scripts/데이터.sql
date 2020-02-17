select user(), database ();

-- title
desc title;
insert into title values
(1,'사장'),
(2,'부장'),
(3,'과장'),
(4,'대리'),
(5,'사원');

select *
from title;

-- department

insert into department values
(1,'영업',8),
(2,'기획',10),
(3,'개발',9),
(4,'총무',7);

-- employee

insert into employee(empno, empname, title, salary, dno, gender, hiredate) values
(1, '이성래', 1, 5000000,2, 0,'2000-03-01'),
(2, '박영권', 3, 3000000,1, 0,'2000-07-01'),
(3, '조민희', 3, 3000000,3, 1,'2005-07-01'),
(4, '이수민', 2, 4000000,3, 1,'2007-07-01'),
(5, '김창섭', 4, 2500000,2, 0,'2010-12-01'),
(6, '최종철', 5, 1500000,3, 0,'2010-12-01');

delete from employee;
select * from employee;

