package ncs_test_htw.ui.service;

import java.util.List;

import ncs_test_htw.dao.DepartmentDao;
import ncs_test_htw.dao.impl.DepartmentDaoImpl;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Title;

public class DepartmentUIService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();

	public List<Department> showDepartmentList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public Department lastNo() {
		return deptDao.selectDepartmentLast();
	}
	public void removeDepartment(Department delDept) {
		 deptDao.deleteDepartment(delDept);
	}

	public void modifyDepartment(Department newDept) {
		deptDao.updateDepartment(newDept);
	}

	public void addDepartment(Department newDept) {
		deptDao.insertDepartment(newDept);
	}
}
