package ncs_test_htw.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_htw.dto.Department;
import ncs_test_htw.dto.Employee;
import ncs_test_htw.ui.content.EmployeePanel;
import ncs_test_htw.ui.list.EmployeeTblPanel;
import ncs_test_htw.ui.service.EmployeeUIService;

public class EmployeeUIPanel extends JPanel implements ActionListener {
	private EmployeePanel pEmployee;
	private EmployeeTblPanel pEmpList;
	private EmployeeUIService service;
	private JButton btnAdd;
	private JButton btnCancel;
	
	
	public EmployeeUIPanel() {
		service = new EmployeeUIService();
		initialize();
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pEmployee = new EmployeePanel();
		pContent.add(pEmployee, BorderLayout.CENTER);
		pEmployee.setService(service);
		pEmployee.setNum(service.lastEmployee());
		pEmployee.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pEmpList = new EmployeeTblPanel();
		pEmpList.loadData(service.showEmployeeList());
		pEmpList.setPopupMenu(createPop());
		pList.add(pEmpList, BorderLayout.CENTER);
	//	pEmpList.setLayout(new BorderLayout(0, 0));
	}

	private JPopupMenu createPop() {
	JPopupMenu pop = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenu);
		pop.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenu);
		pop.add(deleteItem);
		
		return pop;
	}

	ActionListener myPopMenu = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("수정")) {
				Employee upEmp = pEmpList.getSelectedItem();
				pEmployee.setItem(upEmp);
				btnAdd.setText("수정");
			}
			if (e.getActionCommand().equals("삭제")) {
				Employee delEmp = pEmpList.getSelectedItem();
				service.removeEmployee(delEmp);
				pEmpList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		}
	};
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
			}else {
				btnUpdateActionPerformed(e);
			}
		}
	}
	private void btnUpdateActionPerformed(ActionEvent e) {
		Employee newEmp = pEmployee.getItem();
		service.modifyEmployee(newEmp);
		pEmpList.updateRow(newEmp, pEmpList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pEmployee.clearTf();
		pEmployee.setNum(service.lastEmployee());
		pEmployee.setService(service);
		
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Employee newEmp = pEmployee.getItem();
			service.addEmployee(newEmp);
			pEmpList.addItem(newEmp);
			pEmployee.clearTf();
			pEmployee.setNum(service.lastEmployee());
			pEmployee.setService(service);
		}catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "중복되었습니다");
				return;
			}
			e1.printStackTrace();
		}

	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pEmployee.clearTf();
		pEmployee.setService(service);
		btnAdd.setText("추가");
		
	}
}
