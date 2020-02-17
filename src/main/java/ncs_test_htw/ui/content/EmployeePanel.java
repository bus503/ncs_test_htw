package ncs_test_htw.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.dto.Title;
import ncs_test_htw.ui.exception.InvalidCheckException;
import ncs_test_htw.ui.service.EmployeeUIService;

public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfNo;
	private JTextField tfName;
	private JDateChooser tfHireDate;
	private JComboBox<Department> cmbDept;
	private JComboBox<Title> cmbTitle;
	private JSpinner spSalary;
	private JRadioButton radioHe;
	private JRadioButton radioShe;
	private EmployeeUIService service;
	private String dateStr;
	
	public EmployeePanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pContainer = new JPanel();
		add(pContainer, BorderLayout.CENTER);
		pContainer.setLayout(new BoxLayout(pContainer, BoxLayout.X_AXIS));
		
		JPanel gar1 = new JPanel();
		pContainer.add(gar1);
		
		JPanel pCenter = new JPanel();
		pContainer.add(pCenter);
		pCenter.setLayout(new GridLayout(0, 2, -15, 5));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblNo);
		
		tfNo = new JTextField();
		pCenter.add(tfNo);
		tfNo.setColumns(10);
		tfNo.setEnabled(false);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblName);
		
		tfName = new JTextField();
		pCenter.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblTitle);
		
		cmbTitle = new JComboBox();
		pCenter.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblSalary);
		
		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		pCenter.add(spSalary);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblGender);
		
		JPanel pGender = new JPanel();
		pCenter.add(pGender);
		
		radioHe = new JRadioButton("남");
		pGender.add(radioHe);
		
		radioShe = new JRadioButton("여");
		pGender.add(radioShe);
		
		radioHe.setSelected(true);
		radioHe.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblDept);
		
		cmbDept = new JComboBox();
		pCenter.add(cmbDept);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblHireDate);
		
		tfHireDate = new JDateChooser(new Date(), "yyyy-MM-dd hh:mm");
		pCenter.add(tfHireDate);
		
		JPanel gar2 = new JPanel();
		pContainer.add(gar2);
	}
	
	public void setNum(Employee item) {
		dateStr = new SimpleDateFormat("YYYY").format(item.getHireDate());
		int date = Integer.parseInt(dateStr.substring(1, dateStr.length()));
		tfNo.setText(String.format("E%03d%03d",date,  item.getEmpNo()+1));
	}
	

	public void setService(EmployeeUIService service) {
		this.service = service;
		setCmbDeptList(service.showDeptList());
		setCmbTitleList(service.showTitleList());
	}

	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	
	public void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(-1);
	}
	@Override
	public Employee getItem() {
		validCheck();
		int empNo = 0;
		String hundred = tfNo.getText().substring(5);
		String ten = tfNo.getText().substring(6);
		if(hundred.equals("0")) {
			if(ten.equals("0")) {
				empNo = Integer.parseInt(tfNo.getText().substring(7));
			}
			else {
				empNo = Integer.parseInt(tfNo.getText().substring(6, 7));
			}
		}
		else {
			empNo = Integer.parseInt(tfNo.getText().substring(5, 7));
		}
		String empName = tfName.getText().trim();
		Title title =  (Title) cmbTitle.getSelectedItem();
		int salary = (int)spSalary.getValue();	
		int gender = 0;
		if(radioHe.isSelected()) {
			gender=0;
		}else {
			gender=1;
		}
		Department dno = (Department)cmbDept.getSelectedItem();
		Date hireDate = tfHireDate.getDate();
		return new Employee(empNo, empName, title, salary, gender, hireDate, dno);
	}


	@Override
	public void setItem(Employee item) {
		dateStr = new SimpleDateFormat("YYYY").format(item.getHireDate());
		int date = Integer.parseInt(dateStr.substring(1, dateStr.length()));
		tfNo.setText(String.format("E%03d%03d", date, item.getEmpNo()) + " ");
		tfName.setText(item.getEmpName());
		cmbTitle.setSelectedItem(item.getTitle());
		spSalary.setValue(item.getSalary());
		if(item.getGender() ==0) {
			radioHe.setSelected(true);
		}else {
			radioShe.setSelected(true);
		}
		tfHireDate.setDate(item.getHireDate());
		cmbDept.setSelectedItem(item.getDno());
		
	}

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbTitle.setSelectedIndex(-1);
		spSalary.setValue(1500000);
		radioHe.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		tfHireDate.setDate(new Date());
		
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().contentEquals("") ||tfName.getText().equals("") || cmbDept.getSelectedIndex() == -1 || cmbTitle.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
	}

	
	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}

	public JComboBox<Title> getCmbTitle() {
		return cmbTitle;
	}

}
