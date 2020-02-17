-- NCS테스트
DROP SCHEMA IF EXISTS ncs_test_htw;

-- NCS테스트
CREATE SCHEMA ncs_test_htw;

-- 부서
CREATE TABLE ncs_test_htw.department (
	deptno   INT(11)     NOT NULL COMMENT '부서번호', -- 부서번호
	deptname VARCHAR(10) NOT NULL COMMENT '부서명', -- 부서명
	floor    INT(11)     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE ncs_test_htw.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			deptno -- 부서번호
		);

-- 직책
CREATE TABLE ncs_test_htw.title (
	no        INT(11)     NOT NULL COMMENT '번호', -- 번호
	titlename VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE ncs_test_htw.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			no -- 번호
		);

-- 사원
CREATE TABLE ncs_test_htw.employee (
	empno    INT(11)     NOT NULL COMMENT '사원번호', -- 사원번호
	empname  VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title    INT(11)     NULL     COMMENT '직책', -- 직책
	salary   INT(11)     NULL     COMMENT '급여', -- 급여
	gender   TINYINT     NULL     COMMENT '성별', -- 성별
	hiredate DATETIME    NULL     COMMENT '입사일', -- 입사일
	dno      INT(11)     NULL     COMMENT '부서' -- 부서
)
COMMENT '사원';

-- 사원
ALTER TABLE ncs_test_htw.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			empno -- 사원번호
		);

-- 사원
ALTER TABLE ncs_test_htw.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dno -- 부서
		)
		REFERENCES ncs_test_htw.department ( -- 부서
			deptno -- 부서번호
		);

-- 사원
ALTER TABLE ncs_test_htw.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES ncs_test_htw.title ( -- 직책
			no -- 번호
		);
	
-- 사용자추가
drop user if exists 'user_ncs_test_htw'@'localhost';
grant all privileges on ncs_test_htw.* to 'user_ncs_test_htw'@'localhost' identified by 'rootroot';
flush privileges;