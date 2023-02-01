import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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

public class student_option extends JFrame implements ActionListener {

	private JButton btn_add;
	private JButton btn_select;
	private JButton btn_cn;
	private study_list sl;
	database db = new database();
	String id;
	private Student_point sp;

	public student_option(String getid) {
		id = getid;
		setTitle("수강자 폼");
		setSize(250, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		btn_add = new JButton("수강신청");
		btn_select = new JButton("점수조회");
		btn_cn = new JButton("수강취소");

		btn_add.setBounds(30, 10, 170, 40);
		btn_select.setBounds(30, 60, 170, 40);
		btn_cn.setBounds(30, 110, 170, 40);

		add(btn_add);
		add(btn_select);
		add(btn_cn);

		btn_add.addActionListener(this);
		btn_select.addActionListener(this);
		btn_cn.addActionListener(this);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn_add) {
			try {
				boolean rs = db.selectsign(id);
				if (rs) {
					JOptionPane.showMessageDialog(null, "이미 수강신청한 내역이 존재합니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (sl == null) {
						sl = new study_list(id);
					} else {
						sl.dispose();
						sl = new study_list(id);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj == btn_select) {
	
			try {
				boolean rs = db.selectsign(id);
				if (!rs) {
					JOptionPane.showMessageDialog(null, "입력된 정보가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if(sp ==null) {
						sp = new Student_point(id);						
					}else {
						sp.dispose();
						sp = new Student_point(id);						
					}
				
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (obj == btn_cn) {
			try {
				if(db.deletesign(id)) {
					JOptionPane.showMessageDialog(null, "수강취소가 완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "수강신청내역이 없거나 오류입니다. \n 재시도하세요.", "메시지", JOptionPane.INFORMATION_MESSAGE);						
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
