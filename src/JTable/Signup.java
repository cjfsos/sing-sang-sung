package JTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataBase.DAO;

public class Signup extends JFrame {
	JButton idcheck = null;
	JButton signUP = null;
	JPanel np = new JPanel();// 반으로 쪼개서 ID와 ID체크
	JPanel cp = new JPanel();// 반으로 쪼개서 PW와 PW확인
	JPanel sp = new JPanel();// 회원가입 단추
	DAO daoIns = DAO.getInstance();

	Signup() {
		this.setSize(200, 210);

		npSetting();
		cpSetting();
		spSetting();

		this.add(np, "North");
		this.add(cp, "Center");
		this.add(sp, "South");

		this.setLocation(700, 180);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	private void npSetting() {
		np.setLayout(new BorderLayout());
		JPanel Nnp = new JPanel();
		JLabel id = new JLabel("ID");
		JTextField idt = new JTextField(10);
		Nnp.add(id);
		Nnp.add(idt);

		JPanel idNcp = new JPanel();
		idcheck = new JButton("ID확인");
		Nnp.add(idcheck);
		idcheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object act = e.getSource();
				if (act.equals(idcheck)) {
//					daoIns.Idcheck();
				}
			}
		});

		np.add(Nnp, "North");
		np.add(idNcp, "Center");
	}

	private void cpSetting() {
		cp.setLayout(new BorderLayout());
		JPanel Ncp = new JPanel();
		JPanel Scp = new JPanel();

		JLabel pw = new JLabel("PW");
		JTextField pwt = new JTextField(10);
		Ncp.add(pw);
		Ncp.add(pwt);

		JLabel pwCK = new JLabel("PW확인");
		JTextField pwtCK = new JTextField(10);
		Scp.add(pwCK);
		Scp.add(pwtCK);

		cp.add(Ncp, "North");
		cp.add(Scp, "Center");
	}

	private void spSetting() {
		signUP = new JButton("회원 가입");
		sp.add(signUP);
	}
}