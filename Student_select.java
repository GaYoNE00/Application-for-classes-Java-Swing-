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

public class Student_select extends JFrame {
	
	String a = "";
	String header[] = {"ID","이름","학과","학년"};
	DefaultTableModel model = new DefaultTableModel(header,0);
	database db = new database();
	JTable tb;

	public Student_select(String type) {
		a = type;
		setTitle("수강신청자 조회");
		setSize(350,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		JLabel lbl = new JLabel(a + "신청자 조회");
		tb = new JTable();
		JScrollPane scp = new JScrollPane(tb);
		
		lbl.setBounds(50,10,150,30);
		scp.setBounds(20,50,270,180);
		
		add(lbl);
		add(scp);
				
		try {
			db.SelectMystudent(type, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
		
	}

}
