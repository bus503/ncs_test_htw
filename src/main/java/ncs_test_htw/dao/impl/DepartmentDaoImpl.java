package ncs_test_htw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.spi.CopyOnWrite;

import ncs_test_htw.dao.DepartmentDao;
import ncs_test_htw.ds.MysqlDataSource;
import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	public DepartmentDaoImpl() {
	}
	
	public static DepartmentDao getInstance() {
		return instance;
	}

	@Override
	public Department selectDepartmentByDno(Department department) {
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setInt(1, department.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					return getDepartment(rs);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select deptno, deptname, floor from department";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =pstmt.executeQuery()){
				List<Department> list = new ArrayList<>();
				
				while(rs.next()) {
					list.add(getDepartment(rs));
				}
				return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department department) {
		String sql = "insert into department values(?,?,?)";
		int res = 0;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setInt(1, department.getDeptNo());
				pstmt.setString(2, department.getDeptName());
				pstmt.setInt(3, department.getFloor());
				res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteDepartment(Department department) {
		String sql = "delete from department where deptno=?";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public int updateDepartment(Department department) {
		String sql = "update department set deptname =? , floor=? where deptno = ?";
		int res = 0;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setString(1, department.getDeptName());
				pstmt.setInt(2, department.getFloor());
				pstmt.setInt(3, department.getDeptNo());
				res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}


	@Override
	public Department selectDepartmentLast() {
		String sql = "select deptno from department order by deptno desc limit 1";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDeptNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDeptNum(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		return new Department(deptNo);
	}

}
