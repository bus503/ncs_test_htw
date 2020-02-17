package ncs_test_htw.ui.list;

import javax.swing.SwingConstants;

import ncs_test_htw.dto.Department;

@SuppressWarnings("serial")
public class DepartmentTblPanel extends AbstractTblPanel<Department> {
	public DepartmentTblPanel() {
	}
	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150, 50);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"부서번호", "부서명", "위치"};
	}

	@Override
	protected Object[] toArray(Department item) {
		return new Object[] {
				String.format("D%03d", item.getDeptNo()), 
				item.getDeptName(), 
				item.getFloor()};
	}

	@Override
	public void updateRow(Department item, int updateIdx) {
		model.setValueAt(String.format("D%03d", item.getDeptNo()), updateIdx, 0);//학생번호
		model.setValueAt(item.getDeptName(), updateIdx, 1);
		model.setValueAt(item.getFloor(), updateIdx, 2);
	}
}
