package ncs_test_htw.ui.service;

import java.util.List;

import ncs_test_htw.dao.TitleDao;
import ncs_test_htw.dao.impl.TitleDaoImpl;
import ncs_test_htw.dto.Title;

public class TitleUIService {
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}

	public Title lastNo() {
		return titleDao.selectTitleLast();
	}
	public void removeTitle(Title delTitle) {
		titleDao.deleteTitle(delTitle);
	}
	
	public void modifyTitle(Title newTitle) {
		titleDao.updateTitle(newTitle);
	}

	public void addTitle(Title newTitle) {
		titleDao.insertTitle(newTitle);
	}

	

}
