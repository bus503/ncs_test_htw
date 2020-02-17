package ncs_test_htw.dao;

import java.util.List;

import ncs_test_htw.dto.Title;

public interface TitleDao {
	Title selectTitleByNo(Title title);
	Title selectTitleLast();
	List<Title> selectTitleByAll();
	
	int insertTitle(Title title);
	int deleteTitle(Title title);
	int updateTitle(Title title);
}
