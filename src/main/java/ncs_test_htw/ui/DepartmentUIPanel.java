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
import ncs_test_htw.ui.content.DepartmentPanel;
import ncs_test_htw.ui.list.DepartmentTblPanel;
import ncs_test_htw.ui.service.DepartmentUIService;

@SuppressWarnings("serial")
public class DepartmentUIPanel extends JPanel implements ActionListener {
	private DepartmentPanel pDepartment;
	private DepartmentTblPanel pDeptList;
	private DepartmentUIService service;
	private JButton btnAdd;
	private JButton btnCancel;
	
	
	public DepartmentUIPanel() {
		service = new DepartmentUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pDepartment = new DepartmentPanel();
		pDepartment.nextNum(service.lastNo());
		pContent.add(pDepartment, BorderLayout.CENTER);
		pDepartment.setLayout(new GridLayout(0, 2, 15, 0));
		
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
		
		pDeptList = new DepartmentTblPanel();
		pDeptList.loadData(service.showDepartmentList());
		pDeptList.setPopupMenu(createPop());
		pList.add(pDeptList, BorderLayout.NORTH);
		//pDeptList.setLayout(new BorderLayout(0, 0));
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
				Department upDept = pDeptList.getSelectedItem();
				pDepartment.setItem(upDept);
				btnAdd.setText("수정");
			}
			if (e.getActionCommand().equals("삭제")) {
				Department delDept = pDeptList.getSelectedItem();
				service.removeDepartment(delDept);
				pDeptList.removeRow();
			}
		}
	};
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if (e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
			}else {
				btnUpdateActionPerformed(e);
			}
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	private void btnUpdateActionPerformed(ActionEvent e) {
		Department newDept = pDepartment.getItem();
		service.modifyDepartment(newDept);
		pDeptList.updateRow(newDept, pDeptList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pDepartment.clearTf();
		pDepartment.nextNum(service.lastNo());
	}
	private void btnAddActionPerformed(ActionEvent e) {
		try {
			Department newDept = pDepartment.getItem();
			service.addDepartment(newDept);
			pDeptList.addItem(newDept);
			pDepartment.clearTf();
			pDepartment.nextNum(service.lastNo());
			
		}catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "부서번호가 중복되었습니다");
				return;
			}
			e1.printStackTrace();
		}
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pDepartment.clearTf();
		pDepartment.nextNum(service.lastNo());
	}
}
