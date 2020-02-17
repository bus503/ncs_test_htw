package ncs_test_htw.ui.list;

import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import ncs_test_htw.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTblPanel extends AbstractTblPanel<Employee> {
	
	
	private String dateStr;

	public EmployeeTblPanel() {
		
	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(100, 100, 80, 150, 50, 100, 150);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"사원번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}

	@Override
	protected Object[] toArray(Employee item) {
		String gender = null;
		if(item.getGender() == 0) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		dateStr = new SimpleDateFormat("YYYY").format(item.getHireDate());
		int date = Integer.parseInt(dateStr.substring(1, dateStr.length()));
		return new Object[] {
			String.format("E%03d%03d", date, item.getEmpNo()),
			item.getEmpName(),
			String.format("%s(%d)",item.getTitle().getTitleName(), item.getTitle().getTitleNo()),
			String.format("%,d", item.getSalary()),
			gender,
			String.format("%s(%d)", item.getDno().getDeptName(), item.getDno().getDeptNo()),
			String.format("%tF", item.getHireDate())
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		dateStr = new SimpleDateFormat("YYYY").format(item.getHireDate());
		int date = Integer.parseInt(dateStr.substring(1, dateStr.length()));
		model.setValueAt(String.format("E%03d%03d", date, item.getEmpNo()), updateIdx, 0);
		//사원번호
		model.setValueAt(item.getEmpName(), updateIdx, 1);
		//사원명
		model.setValueAt(item.getTitle(), updateIdx, 2);
		//직책
		model.setValueAt(String.format("%,d", item.getSalary()), updateIdx, 3);
		//급여
		String gender = null;
		if(item.getGender() == 0) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		model.setValueAt(gender, updateIdx, 4); 
		model.setValueAt(item.getDno(), updateIdx, 5);
		model.setValueAt(String.format("%tF", item.getHireDate()), updateIdx, 6);
	}

}
