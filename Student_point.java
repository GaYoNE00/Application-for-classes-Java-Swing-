import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Student_point extends JFrame implements ActionListener {
	
	String a = "이름A";
	String header[] = {"과목","점수","강사명"};
	DefaultTableModel model = new DefaultTableModel(header,0);
	JTable tb;
	database db = new database();
	private JButton btn_me;
	public Student_point(String id) {
		setTitle("수강자 점수");
		setSize(300,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		JLabel lbl = new JLabel(a + "신청자 조회");
		tb = new JTable();
		JScrollPane scp = new JScrollPane(tb);
		btn_me = new JButton("메뉴로");
		
		lbl.setBounds(50,10,150,30);
		scp.setBounds(20,50,230,110);
		btn_me.setBounds(80,170,80,30);
		
		btn_me.addActionListener(this);
		
		add(lbl);
		add(scp);
		add(btn_me);
		try {
			db.select_point(id, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lbl.setText(a + "신청자 조회");
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn_me) {
			this.dispose();
		}
	}

}
