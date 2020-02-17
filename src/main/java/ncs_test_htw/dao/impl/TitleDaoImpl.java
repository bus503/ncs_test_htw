package ncs_test_htw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.spi.CopyOnWrite;

import ncs_test_htw.dao.TitleDao;
import ncs_test_htw.ds.MysqlDataSource;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;

public class TitleDaoImpl implements TitleDao {
	private static final TitleDaoImpl instance = new TitleDaoImpl();

	public TitleDaoImpl() {
	}

	public static TitleDao getInstance() {
		return instance;
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select no, titlename from title where no=?";
		try (Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTitleNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				LogUtil.prnLog(pstmt);
				if (rs.next()) {
					return getTitle(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select no, titlename from title";// sql문 던지고
		try (Connection con = MysqlDataSource.getConnection(); // 데이터베이스 커넥션 생성
				PreparedStatement pstmt = con.prepareStatement(sql); // statement 생성
				ResultSet rs = pstmt.executeQuery()) {// 쿼리실행

			List<Title> list = new ArrayList<>(); // 쿼리를 담을 리스트 생성

			while (rs.next()) {
				list.add(getTitle(rs));// 쿼리 결과를 리스트에 담는다
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("no");
		String titleName = rs.getString("titlename");
		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title title) {
		String sql ="insert into title values(?,?)";
		int res =0;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where no=?";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set titlename =? where no =?";
		int res = 0;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, title.getTitleName());
			pstmt.setInt(2, title.getTitleNo());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Title selectTitleLast() {
		String sql = "select no from title order by no desc limit 1";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getTitleNum(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitleNum(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("no");
		return new Title(titleNo);
	}
}
