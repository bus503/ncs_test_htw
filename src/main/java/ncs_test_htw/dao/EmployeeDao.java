package ncs_test_htw.dao;

import java.util.List;

import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.dto.Title;

public interface EmployeeDao {
	Employee selectEmployeeByEno(Employee employee);
	Employee selectEmployeeLastData();
	List<Employee> selectEmployeeByAll();
	
	int insertEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	int updateEmployee(Employee employee);
	
}
