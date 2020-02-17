package ncs_test_htw.dao;

import java.util.List;

import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Title;

public interface DepartmentDao {
	Department selectDepartmentByDno(Department department);
	Department selectDepartmentLast();
	List<Department> selectDepartmentByAll();
	
	int insertDepartment(Department department);
	int deleteDepartment(Department department);
	int updateDepartment(Department department);
}
