package ncs_test_htw.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_htw.dao.impl.EmployeeDaoImpl;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = EmployeeDaoImpl.getinstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao=null;
	}

	@Test
	public void test01SelectEmployeeByENo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = dao.selectEmployeeByEno(new Employee(1003));
		Assert.assertNotNull(emp);
		LogUtil.prnLog(String.format("%1$tF - %1$tT %1$tY년 %1$tm월 %1$td일 %1$tH시 %1$tM분 %1$tS초 ", emp.getHireDate()));
		
		Calendar hireDate = Calendar.getInstance();
		hireDate.setTime(emp.getHireDate());
		
		LogUtil.prnLog(String.format("%d 년", hireDate.get(Calendar.YEAR)));
		LogUtil.prnLog(String.format("%d 월", hireDate.get(Calendar.MONTH) + 1));
		LogUtil.prnLog(String.format("%d 일", hireDate.get(Calendar.DAY_OF_MONTH)));
		LogUtil.prnLog(String.format("%d시 %d분 %d초", hireDate.get(Calendar.HOUR_OF_DAY), hireDate.get(Calendar.MINUTE), hireDate.get(Calendar.SECOND)));
		
	}

	@Test
	public void test02SelectEmployeeByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotEquals(0, list.size());
	
	}

	@Test
	public void test03InsertEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar c = Calendar.getInstance();
		Date hireDate  = new Date(c.getTimeInMillis());
		Employee emp = new Employee(1004, "이유영", new Title(2), 2000000, 1, hireDate, new Department(2));
		LogUtil.prnLog(emp);
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
		
	}

	@Test
	public void test04UpdateEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar c = Calendar.getInstance();
		Date hireDate  = new Date(c.getTimeInMillis());
		Employee emp = new Employee(1004, "수지", new Title(3), 2500000, 0, hireDate, new Department(3));
		int res = dao.updateEmployee(emp);
		LogUtil.prnLog(res);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test05DeleteEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee(1004);
	    int res = dao.deleteEmployee(emp);
	    LogUtil.prnLog(res);
	    Assert.assertEquals(1, res);
	}
}
