import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class study_option extends JFrame implements ActionListener {
	int rdox = 100;
	int rdoy = 40;
	private JButton btn_ok;
	boolean ch_1, ch_2, ch_3, ch_4;
	private database db = new database();
	private String id;
	private ButtonGroup grp[] = new ButtonGroup[4];
	private JRadioButton rdo1[] = new JRadioButton[4];
	private JRadioButton rdo2[] = new JRadioButton[4];
	private JRadioButton rdo3[] = new JRadioButton[4];
	private JRadioButton rdo4[] = new JRadioButton[4];
	private String word, java, excel, ppt;
 
	public study_option(String getid, boolean getch_1, boolean getch_2, boolean getch_3, boolean getch_4) {
		id = getid;
		ch_1 = getch_1;
		ch_2 = getch_2;
		ch_3 = getch_3;
		ch_4 = getch_4;

		setTitle("수강과목");
		setSize(250, 280);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		for (int g = 0; g < grp.length; g++) {
			grp[g] = new ButtonGroup();
		}
		for (int i = 0; i < rdo1.length; i++) {
			rdo1[i] = new JRadioButton("");
			rdo1[i].setBounds(rdox, rdoy, 20, 20);
			this.add(rdo1[i]);
			grp[0].add(rdo1[i]);
			rdox += 30;
		}
		rdox = 100;
		rdoy += 40;
		for (int i = 0; i < rdo2.length; i++) {
			rdo2[i] = new JRadioButton("");
			rdo2[i].setBounds(rdox, rdoy, 20, 20);
			this.add(rdo2[i]);
			grp[1].add(rdo2[i]);
			rdox += 30;
		}
		rdox = 100;
		rdoy += 40;
		for (int i = 0; i < rdo3.length; i++) {
			rdo3[i] = new JRadioButton("");
			rdo3[i].setBounds(rdox, rdoy, 20, 20);
			this.add(rdo3[i]);
			grp[2].add(rdo3[i]);
			rdox += 30;
		}
		rdox = 100;
		rdoy += 40;
		for (int i = 0; i < rdo4.length; i++) {
			rdo4[i] = new JRadioButton("");
			rdo4[i].setBounds(rdox, rdoy, 20, 20);
			this.add(rdo4[i]);
			grp[3].add(rdo4[i]);
			rdox += 30;
		}

		JLabel lbl_t = new JLabel("초급 중급 고급 전문가");
		JLabel lbl_1 = new JLabel("Word");
		JLabel lbl_2 = new JLabel("Java");
		JLabel lbl_3 = new JLabel("Excel");
		JLabel lbl_4 = new JLabel("PowerPoint");

		btn_ok = new JButton("입력");

		lbl_t.setBounds(100, 10, 200, 20);
		lbl_1.setBounds(10, 40, 80, 20);
		lbl_2.setBounds(10, 80, 80, 20);
		lbl_3.setBounds(10, 120, 80, 20);
		lbl_4.setBounds(10, 160, 100, 20);
		btn_ok.setBounds(70, 200, 120, 30);

		add(lbl_t);
		add(lbl_1);
		add(lbl_2);
		add(lbl_3);
		add(lbl_4);
		add(btn_ok);

		if (!ch_1) {
			for (int i = 0; i < 4; i++) {
				rdo1[i].setEnabled(false);
			}

		}
		if (!ch_2) {
			for (int i = 0; i < 4; i++) {
				rdo2[i].setEnabled(false);
			}
		}
		if (!ch_3) {
			for (int i = 0; i < 4; i++) {
				rdo3[i].setEnabled(false);
			}
		}
		if (!ch_4) {
			for (int i = 0; i < 4; i++) {
				rdo4[i].setEnabled(false);
			}
		}

		btn_ok.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn_ok) {
			System.out.println(grp[0].isSelected(null));
			System.out.println(ch_1);

			if ((!grp[0].isSelected(null) == false && ch_1 == true)
					|| (!grp[1].isSelected(null) == false && ch_2 == true)
					|| (!grp[2].isSelected(null) == false && ch_3 == true)
					|| (!grp[3].isSelected(null) == false && ch_4 == true)) {
				JOptionPane.showMessageDialog(null, "모든 강좌 수준을 선택해주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);

			} else {
				int j=0;
				for (int i = 0; i < 4; i++) {
					if (rdo1[i].isSelected()) {
						switch (i) {
						case 0:
							word = "초급";
							break;
						case 1:
							word = "중급";
							break;
						case 2:
							word = "고급";
							break;
						case 3:
							word = "전문가";
							break;
						default:
							word = "오류";
							break;
						}
						j+=1;
					}else if(j!=1){
						word = "신청안함";						
					}
				}
				j=0;

				for (int i = 0; i < 4; i++) {
					if (rdo2[i].isSelected()) {
						switch (i) {
						case 0:
							java = "초급";
							break;
						case 1:
							java = "중급";
							break;
						case 2:
							java = "고급";
							break;
						case 3:
							java = "전문가";
							break;
						default:
							java = "오류";
							break;
						}
						j+=1;
					}else if(j!=1){
						java = "신청안함";						
					}
				}
				
				j=0;
				for (int i = 0; i < 4; i++) {
					if (rdo3[i].isSelected()) {
						switch (i) {
						case 0:
							excel = "초급";
							break;
						case 1:
							excel = "중급";
							break;
						case 2:
							excel = "고급";
							break;
						case 3:
							excel = "전문가";
							
							break;
						default:
							excel = "오류";
							break;
							
						}
						j+=1;
					}else if(j!=1){
						excel = "신청안함";						
					}
				}
				j=0;
				for (int i = 0; i < 4; i++) {
					if (rdo4[i].isSelected()) {
						switch (i) {
						case 0:
							ppt = "초급";
							break;
						case 1:
							ppt = "중급";
							break;
						case 2:
							ppt = "고급";
							break;
						case 3:
							ppt = "전문가";
							break;
						default:
							ppt = "오류";
							break;
						}
						j +=1;
					}else if(j!=1) {
						ppt = "신청안함";						
					}
				}
				try {
					System.out.println(word);
					System.out.println(java);
					System.out.println(excel);
					System.out.println(ppt);
					boolean result = db.signup(id, ch_1, ch_2, ch_3, ch_4, word, java, excel, ppt);
					if (result) {
						JOptionPane.showMessageDialog(null, "과목이 선택되었습니다", "메시지", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "과목 등록 실패했습니다", "메시지", JOptionPane.INFORMATION_MESSAGE);						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

}
