import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class study_list extends JFrame implements ActionListener {
		
	private JButton btn_ok;
	private JCheckBox cb_1;
	private JCheckBox cb_2;
	private JCheckBox cb_3;
	private JCheckBox cb_4;
	private study_option so;
	String id="";
	public study_list(String getid) {
		id = getid;
		setTitle("수강과목");
		setSize(250,280);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
				
		cb_1 = new JCheckBox("Word");
		cb_2 = new JCheckBox("Java");
		cb_3 = new JCheckBox("Excel");
		cb_4 = new JCheckBox("PowerPoint");
		

		btn_ok = new JButton("강좌수준선택");
		
		cb_1.setBounds(50, 10, 80, 20);
		cb_2.setBounds(50, 60, 80, 20);
		cb_3.setBounds(50, 110, 80, 20);
		cb_4.setBounds(50, 160, 100, 20);
		btn_ok.setBounds(70, 200, 120, 30);
		
		add(cb_1);
		add(cb_2);
		add(cb_3);
		add(cb_4);
		add(btn_ok);
		
		btn_ok.addActionListener(this);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn_ok) {
			if(!cb_1.isSelected()&&!cb_2.isSelected()&&!cb_3.isSelected()&&!cb_4.isSelected()) {
				JOptionPane.showMessageDialog(null, "수강하실 강좌명을 선택해주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else {
				this.dispose();
				if(so == null) {
					so = new study_option(id, cb_1.isSelected(),cb_2.isSelected(),cb_3.isSelected(),cb_4.isSelected());					
				}else {
					so.dispose();
					so = new study_option(id, cb_1.isSelected(),cb_2.isSelected(),cb_3.isSelected(),cb_4.isSelected());					
				}
			}
		}
	}

}
