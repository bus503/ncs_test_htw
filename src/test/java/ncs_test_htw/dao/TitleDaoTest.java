package ncs_test_htw.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_htw.dao.impl.TitleDaoImpl;
import ncs_test_htw.dto.Title;
import ncs_test_htw.util.LogUtil;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = TitleDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao =null;
	}
	

	@Test
	public void test01SelectTitleByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = dao.selectTitleByNo(new Title(3));
		Title res = new Title(3);
		Assert.assertNotEquals(res, 3);
		LogUtil.prnLog(title);
	}

	@Test
	public void test02SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> lists = dao.selectTitleByAll();
		Assert.assertNotNull(lists);
		
		for(Title title: lists) {
			LogUtil.prnLog(title);
		}
	}

	@Test
	public void test03InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(6,"쌈장");
		int res = dao.insertTitle(title);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(title);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title =new Title(6,"실장");
		int res = dao.updateTitle(title);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(title);
	}
	
	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(6);
		int res = dao.deleteTitle(title);
		Assert.assertNotEquals(-1, res);
		LogUtil.prnLog(title);
	}
}
