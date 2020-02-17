package ncs_test_htw.ui.service;

import java.util.List;

import ncs_test_htw.dao.DepartmentDao;
import ncs_test_htw.dao.EmployeeDao;
import ncs_test_htw.dao.TitleDao;
import ncs_test_htw.dao.impl.DepartmentDaoImpl;
import ncs_test_htw.dao.impl.EmployeeDaoImpl;
import ncs_test_htw.dao.impl.TitleDaoImpl;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.dto.Title;

public class EmployeeUIService {
	private EmployeeDao empDao = EmployeeDaoImpl.getinstance();
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();

	public List<Employee> showEmployeeList() {
		return empDao.selectEmployeeByAll();
	}
	
	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public void removeEmployee(Employee delEmp) {
		 empDao.deleteEmployee(delEmp);
	}

	public void modifyEmployee(Employee newEmp) {
		empDao.updateEmployee(newEmp);
	}

	public void addEmployee(Employee newEmp) {
		empDao.insertEmployee(newEmp);
	}

	public Employee lastEmployee() {
		return empDao.selectEmployeeLastData();
	}

	
	
}
