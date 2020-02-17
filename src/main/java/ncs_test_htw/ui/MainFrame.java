package ncs_test_htw.ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnEmployee;
	private JButton btnDepartment;
	private JButton btnTitle;
	private JFrame employeeFrame;
	private JFrame departmentFrame;
	private JFrame titleFrame;

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		initialize();
	}
	private void initialize() {
		setTitle("ERP관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 10, 0));
		
		btnEmployee = new JButton("사원관리");
		btnEmployee.addActionListener(this);
		panel.add(btnEmployee);
		
		btnDepartment = new JButton("부서관리");
		btnDepartment.addActionListener(this);
		panel.add(btnDepartment);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		panel.add(btnTitle);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		}
		if (e.getSource() == btnDepartment) {
			btnDepartmentActionPerformed(e);
		}
		if (e.getSource() == btnEmployee) {
			btnEmployeeActionPerformed(e);
		}
	}

	protected void btnEmployeeActionPerformed(ActionEvent e) {
		if(employeeFrame == null) {
			employeeFrame = new JFrame();
			employeeFrame.setBounds(100, 100, 550, 700);
			employeeFrame.setTitle("사원 관리");
			EmployeeUIPanel eu = new EmployeeUIPanel();
			employeeFrame.getContentPane().add(eu);
			employeeFrame.setVisible(true);
			
		}else {
			if(employeeFrame.isVisible()) {
				return;
			}
			employeeFrame.setVisible(true);
		}
	}
	

	protected void btnDepartmentActionPerformed(ActionEvent e) {
		if(departmentFrame==null) {
			departmentFrame = new JFrame();
			departmentFrame.setBounds(100, 100, 450, 400);
			departmentFrame.setTitle("부서 관리");
			DepartmentUIPanel dp = new DepartmentUIPanel();
			departmentFrame.getContentPane().add(dp);
			departmentFrame.setVisible(true);
		}else {
			if(departmentFrame.isVisible()) {
				return;
			}
			departmentFrame.setVisible(true);
		}
		
	}
	protected void btnTitleActionPerformed(ActionEvent e) {
		if(titleFrame == null) {
			titleFrame = new JFrame();
			titleFrame.setBounds(100, 100, 450, 400);
			titleFrame.setTitle("직책 관리");
			TitleUIPanel tp = new TitleUIPanel();
			titleFrame.getContentPane().add(tp);
			titleFrame.setVisible(true);
		}else {
			if(titleFrame.isVisible()) {
				return;
			}
			titleFrame.setVisible(true);
		}
	}
}
