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

public class All_Student_select extends JFrame {
	
	String header[] = {"ID","이름","성별","학과","학년"};
	DefaultTableModel model = new DefaultTableModel(header,0);
	database db = new database();
	JTable tb;

	public All_Student_select() {
		setTitle("수강신청자 조회");
		setSize(350,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		JLabel lbl = new JLabel("전체 수강자 조회");
		tb = new JTable();
		JScrollPane scp = new JScrollPane(tb);
		
		lbl.setBounds(50,10,150,30);
		scp.setBounds(20,50,300,150);
		
		add(lbl);
		add(scp);
				
		try {
			db.AllSelectMystudent(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
		
	}

}
