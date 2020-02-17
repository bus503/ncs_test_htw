package ncs_test_htw.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Title;
import ncs_test_htw.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbsItemPanel<Department> {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfFloor;
	private JLabel lblNo;
	private JLabel lblName;
	private JLabel lblFloor;

	public DepartmentPanel() {
		initialize();
	}
	
	private void initialize() {
		setBorder(new TitledBorder(null, "\uBD80\uC11C \uAD00\uB9AC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		tfNo.setEnabled(false);
		
		lblName = new JLabel("부서명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
		
		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		add(tfFloor);
		tfFloor.setColumns(10);
	}

	@Override
	public Department getItem() {
		validCheck();
		int deptNo = Integer.parseInt(tfNo.getText().substring(3));
		String deptName = tfName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	public void nextNum(Department item) {
		tfNo.setText(String.format("D%03d", item.getDeptNo()+1));
	}
	@Override
	public void setItem(Department item) {
		tfNo.setText(String.format("D%03d",item.getDeptNo()));
		tfName.setText(item.getDeptName());
		tfFloor.setText(item.getFloor()+"");
	}

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		tfFloor.setText("");
		
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().contentEquals("") || tfName.getText().contentEquals("") || tfFloor.getText().equals("")) {
			throw new InvalidCheckException();
		}
	}

}
