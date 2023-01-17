import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class main_frame extends JFrame implements MouseListener{
	
	private JButton btn_Tlogin;
	private JButton btn_Slogin;
	private JButton btn_select;
	private Loginform lf;
	private All_Student_select ass;

	public main_frame() {
		setTitle("메인");
		setSize(250,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
	
		btn_Tlogin =  new JButton("강사로그인");
		btn_Slogin =  new JButton("수강자로그인");
		btn_select =  new JButton("전체수강자조회");
		
		btn_Tlogin.setBounds(30,30,180,50);
		btn_Slogin.setBounds(30,100,180,50);
		btn_select.setBounds(30,170,180,50);
		

		btn_Tlogin.addMouseListener(this);
		btn_Slogin.addMouseListener(this);
		btn_select.addMouseListener(this);
		
		add(btn_Tlogin);
		add(btn_Slogin);
		add(btn_select);
		
		setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj==btn_Tlogin&&e.getClickCount()==2 && e.getButton()==e.BUTTON1) {
			if(lf==null) {
				lf = new Loginform(0);
			}else {
				lf.dispose();
				lf = new Loginform(0);				
			}
				
		}else if(obj==btn_Slogin&&e.getClickCount()==2 && e.getButton()==e.BUTTON1) {
			if(lf==null) {
				lf = new Loginform(1);
			}else {
				lf.dispose();
				lf = new Loginform(1);
			}
		}else if(obj==btn_select&&e.getClickCount()==2 && e.getButton()==e.BUTTON1) {
			if(ass==null) {
				ass = new All_Student_select();
			}else {
				ass.dispose();
				ass = new All_Student_select();
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
