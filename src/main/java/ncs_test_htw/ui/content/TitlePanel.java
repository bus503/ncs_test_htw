package ncs_test_htw.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ncs_test_htw.dto.Title;
import ncs_test_htw.ui.exception.InvalidCheckException;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TitlePanel extends AbsItemPanel<Title> {
	private JTextField tfNo;
	private JTextField tfName;
	
	public TitlePanel() {
		
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setEnabled(false);
		add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
	}
	
	
	public void nextNum(Title item) {
		tfNo.setText(String.format("T%03d", item.getTitleNo()+1));
	}
	
	@Override
	public Title getItem() {
		int titleNo = Integer.parseInt(tfNo.getText().substring(3));
		String titleName = tfName.getText().trim();
		return new Title(titleNo, titleName);
	}

	@Override
	public void setItem(Title item) {
		tfNo.setText(String.format("T%03d", item.getTitleNo()));
		tfName.setText(item.getTitleName());
		
	}
	
	

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().equals("") || tfName.getText().equals("")) {
			throw new InvalidCheckException();
		}
	}

}
