package ncs_test_htw.ui.list;

import javax.swing.SwingConstants;

import ncs_test_htw.dto.Title;

@SuppressWarnings("serial")
public class TitleTblPanel extends AbstractTblPanel<Title> {
	public TitleTblPanel() {

	}
	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150);
		tableCellAlign(SwingConstants.CENTER, 0, 1);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"번호","직책명"};
		
	}

	@Override
	protected Object[] toArray(Title item) {
		return new Object[] {String.format("T%03d", item.getTitleNo()),	
				item.getTitleName()};
	}

	@Override
	public void updateRow(Title item, int updateIdx) {
		model.setValueAt(String.format("T%03d", item.getTitleNo()), updateIdx, 0);
		model.setValueAt(item.getTitleName(), updateIdx, 1);
	}

}
