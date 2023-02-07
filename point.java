import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class point extends JFrame implements ActionListener {
	

	String[] pt = {"0","1","2","3","4","5"};
	database db = new database();
	private JComboBox<String> cm_id;
	String type="";
	private JButton btn_ok;
	private JButton btn_cn;
	private JComboBox<String> cm_pt;
	
	public point(String gettype) {
		type = gettype;
		setTitle("점수등록");
		setSize(250,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		
		
		JLabel lbl_id = new JLabel("ID");				
		JLabel lbl_point = new JLabel("점수");
		
		cm_id = new JComboBox<String>();
		cm_pt = new JComboBox<String>(pt);
		
		btn_ok = new JButton("확인");
		btn_cn = new JButton("취소");
		
		lbl_id.setBounds(30,30,30,20);
		lbl_point.setBounds(30,60,30,20);
		
		cm_id.setBounds(80,30,100,20);
		cm_pt.setBounds(80,60,100,20);
		
		btn_ok.setBounds(40, 100, 60, 30);
		btn_cn.setBounds(130, 100, 60, 30);
		
		btn_ok.addActionListener(this);
		btn_cn.addActionListener(this);
		
		add(lbl_id);
		add(lbl_point);
		add(cm_id);
		add(cm_pt);
		add(btn_ok);
		add(btn_cn);
		
		try {
			db.SelectMystudentID(type, cm_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btn_ok) {
			try {
				if(db.UpdatePoint(cm_pt.getSelectedItem().toString(), cm_id.getSelectedItem().toString(), type)) {
					JOptionPane.showMessageDialog(null, type + " 점수가 입력되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, type + " 점수가 입력되지 않았습니다..", "메시지", JOptionPane.INFORMATION_MESSAGE);					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(obj == btn_cn) {
			this.dispose();
		}
	}

}
