package ncs_test_htw.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_htw.dao.impl.DepartmentDaoImpl;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = DepartmentDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao=null;
	}

	@Test
	public void test01SelectDepartmentByDno() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = dao.selectDepartmentByDno(new Department(3));
		Department res = new Department(3);
		Assert.assertNotEquals(res, 3);
		LogUtil.prnLog(department);
	}

	@Test
	public void test02SelectDepartmentByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> lists = dao.selectDepartmentByAll();
		Assert.assertNotNull(lists);
		
		for(Department department : lists) {
			LogUtil.prnLog(department);
		}
	}

	@Test
	public void test03InsertDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5, "김장", 6);
		int res = dao.insertDepartment(department);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(department);
	
	}

	@Test
	public void test04UpdateDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5,"법무",7);
		int res = dao.updateDepartment(department);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(department);
		
	}
	
	@Test
	public void test05DeleteDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5);
		int res = dao.deleteDepartment(department);
		Assert.assertNotEquals(-1, res);
		LogUtil.prnLog(department);
		
	}

}
