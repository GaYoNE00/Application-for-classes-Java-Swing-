import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class main_frame2 extends JFrame implements MouseListener{
	
	private JButton btn_select;
	private JButton btn_set_point;
	private Loginform lf;
	private Student_select std_s;
	private point p;
	private String type;
	
	public main_frame2(String gettype) {
		type = gettype;
		setTitle("강사메뉴");
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
	

		btn_select =  new JButton("수강자조회");
		btn_set_point =  new JButton("점수입력");
		

		btn_select.setBounds(50,20,180,50);
		btn_set_point.setBounds(50,90,180,50);

		btn_select.addMouseListener(this);
		btn_set_point.addMouseListener(this);
		

		add(btn_select);
		add(btn_set_point);
		
		setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj==btn_select&&e.getClickCount()==2 && e.getButton()==e.BUTTON1) {
			if(std_s == null) {
				std_s = new Student_select(type);
			}else {
				std_s.dispose();
				std_s = new Student_select(type);
			}
				
		}else if(obj==btn_set_point&&e.getClickCount()==2 && e.getButton()==e.BUTTON1) {
			if(p == null) {
				p = new point(type);
			}else {
				p.dispose();
				p = new point(type);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
