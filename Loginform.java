import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Loginform extends JFrame implements ActionListener {
	int a;
	private JButton btn_ok;
	private JButton btn_cs;
	private JTextField tf_id;
	private JPasswordField tf_pw;
	private database db;
	private String loginresult = "";
	private main_frame2 mf2;
	private student_option so;

	public Loginform(int geta) {
		a = geta;
		setTitle("로그인");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lbl_id = new JLabel("ID");
		JLabel lbl_pw = new JLabel("비밀번호");

		tf_id = new JTextField();
		tf_pw = new JPasswordField();

		btn_ok = new JButton("확인");
		btn_cs = new JButton("취소");

		lbl_id.setBounds(55, 40, 50, 20);
		lbl_pw.setBounds(20, 70, 50, 20);

		tf_id.setBounds(100, 40, 150, 20);
		tf_pw.setBounds(100, 70, 150, 20);

		btn_ok.setBounds(50, 110, 80, 30);
		btn_cs.setBounds(150, 110, 80, 30);

		btn_ok.addActionListener(this);
		btn_cs.addActionListener(this);

		add(lbl_id);
		add(lbl_pw);
		add(tf_id);
		add(tf_pw);
		add(btn_ok);
		add(btn_cs);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btn_ok) {
			if (tf_id.getText().equals("") || tf_pw.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요!", "로그인 오류", JOptionPane.ERROR_MESSAGE);
			} else {
				db = new database();
				try {
					loginresult = db.logincheck(a, tf_id.getText().toString(), tf_pw.getText().toString());
					if (loginresult.equals("db")) {
						JOptionPane.showMessageDialog(null, "데이터 베이스 오류!", "관리자 문의", JOptionPane.ERROR_MESSAGE);
					} else if (loginresult.equals("no")) {
						JOptionPane.showMessageDialog(null, "아이디를 다시 입력해주세요", "메시지", JOptionPane.ERROR_MESSAGE);
						tf_id.setText("");
						tf_pw.setText("");
						tf_id.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "로그인이완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						if (loginresult.equals("java") || loginresult.equals("excel") || loginresult.equals("word")
								|| loginresult.equals("ppt")) {
							mf2 = new main_frame2(loginresult);
						} else {
							so = new student_option(loginresult);
						}
						this.dispose();
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (obj == btn_cs) {
			dispose();
		}
	}
}
