package ncs_test_htw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ncs_test_htw.dao.EmployeeDao;
import ncs_test_htw.ds.MysqlDataSource;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	
	public EmployeeDaoImpl() {
	
	}
	public static EmployeeDao getinstance() {
		return instance;
	}

	@Override
	public Employee selectEmployeeByEno(Employee employee) {
	//	String sql = "select empno, empname, title, salary, gender, hiredate, dno from employee where empno = ?";
		String sql = "select e.empno, e.empname, t.no, t.titlename, e.salary, e.gender, d.deptno, d.deptname, d.floor, e.hiredate from employee e left join title t on e.title = t.no left join department d on e.dno = d.deptno where e.empno=?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setInt(1, employee.getEmpNo());
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						return getEmployee(rs);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
//		String sql = "select empno, empname, title, salary, gender, hiredate, dno from employee";
		String sql = "select e.empno, e.empname, t.no, t.titlename, e.salary, e.gender, d.deptno, d.deptname, d.floor, e.hiredate from employee e left join title t on e.title = t.no left join department d on e.dno = d.deptno";

		List<Employee> list = null;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =pstmt.executeQuery()){
				LogUtil.prnLog(pstmt);
			if(rs.next()) {
				list = new ArrayList<>();
				do {	
					list.add(getEmployeeJoin(rs));
				}while(rs.next());
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Employee getEmployeeJoin(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("no"), rs.getString("titlename"));
		int salary = rs.getInt("salary");
		int gender = rs.getInt("gender");
		Department dno = new Department(rs.getInt("deptno"));
		dno.setDeptName(rs.getString("deptname"));
		dno.setFloor(rs.getInt("floor"));
		Date hireDate = rs.getTimestamp("hiredate");
		return new Employee(empNo, empName, title, salary, gender, hireDate, dno);
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title"));
		int salary = rs.getInt("salary");
		int gender = rs.getInt("gender");
		Date hireDate = rs.getTimestamp("hiredate");
		Department dno = new Department(rs.getInt("dno"));
		Employee emp = new Employee(empNo, empName, title, salary, gender, hireDate, dno);
		LogUtil.prnLog(emp);
		return emp;
		
	}
	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values (?, ?, ?, ?, ?, ? ,?)";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTitleNo());// title
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getGender());
			pstmt.setTimestamp(6, new Timestamp(employee.getHireDate().getTime()));// 입사일
			pstmt.setInt(7, employee.getDno().getDeptNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
//		String sql = "update employee set empname = ?, title=? ,salary = ?, gender=?,hiredate =?,dno=? where empno =?";
//		try(Connection con = MysqlDataSource.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(sql)){
//			pstmt.setString(1, employee.getEmpName());
//			pstmt.setInt(2, employee.getTitle().getTitleNo());// title
//			pstmt.setInt(3, employee.getSalary());
//			pstmt.setInt(4, employee.getGender());
//			pstmt.setTimestamp(5, new Timestamp(employee.getHireDate().getTime()));// 입사일
//			pstmt.setInt(6, employee.getDno().getDeptNo());
//			pstmt.setInt(7, employee.getEmpNo());
//			LogUtil.prnLog(pstmt);
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
		
		StringBuilder sql = new StringBuilder("update employee set ");
		if(employee.getEmpName()!=null) sql.append("empname=?, ");
		if(employee.getTitle()!=null) sql.append("title=?, ");
		if(employee.getSalary()!=0) sql.append("salary=?, ");
		if(employee.getGender()!=-1) sql.append("gender=?, ");
		if(employee.getDno()!=null) sql.append("dno=?, ");
		if(employee.getHireDate()!=null) sql.append("hiredate=?, ");
		sql.replace(sql.lastIndexOf(","), sql.length(), " ");
		sql.append("where empno=?");
		
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			int argCnt = 1;
			if(employee.getEmpName()!=null) pstmt.setString(argCnt++, employee.getEmpName());
			if(employee.getTitle()!=null) pstmt.setInt(argCnt++, employee.getTitle().getTitleNo());
			if(employee.getSalary()!=0) pstmt.setInt(argCnt++, employee.getSalary());
			if(employee.getGender()!=-1) pstmt.setInt(argCnt++, employee.getGender());
			if(employee.getDno()!=null) pstmt.setInt(argCnt++, employee.getDno().getDeptNo());
			if(employee.getHireDate()!=null) pstmt.setTimestamp(argCnt++, new Timestamp(employee.getHireDate().getTime()));
			pstmt.setInt(argCnt++, employee.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where empno = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);){
			pstmt.setInt(1, employee.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Employee selectEmployeeLastData() {
		String sql = "select empno,hiredate from employee order by empno desc limit 1";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmpNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmpNum(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		Employee emp = new Employee(empNo);
		emp.setHireDate(rs.getTimestamp("hiredate"));
		return emp;
	}


}
