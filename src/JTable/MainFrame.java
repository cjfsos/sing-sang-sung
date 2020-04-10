package JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Client.Ccenter;
import Client.CenterC;
import DataBase.DAO;

public class MainFrame extends JFrame{
	String header[] = { "번호", "곡명", "가수명", "앨범", "장르" };
	String contents[][];
	public DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	JTable table = new JTable(tableModel);
	JScrollPane Scroll = new JScrollPane(table);
	JPanel wp = new JPanel();
	JPanel ep = new JPanel();
	JLabel id;
	JLabel pw;
	JTextField ID;
	JPasswordField PW;
	JButton signIn;
	JButton signUp;
	JPanel midNp = null;
	JPanel smNp = null;
	JPanel smCp = null;
	JPanel smSp = null;
	JPanel smSsp = null;
	JPanel smSnp = null;
	JLabel loginMSG = null;
	JPanel midSp = null;
	JButton suggest = null;
	JButton listen = null;
	JPanel midNplg = null;
	JButton myinfo = null;
	JButton logout = null;
	DAO daoIns = DAO.getInstance();
	ArrayList<String[]> DTList = new ArrayList<>();
	CenterC Cct;
	String orderMSG;

	public MainFrame(CenterC order) {
		Cct = order;
		Dimension size = new Dimension(700, 500);
		this.setSize(size);
		WestSetting();
		EastSetting();
		this.add(Scroll);
		this.add(ep, "East");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	private void WestSetting() {
//		Cct.send("need_SList_Data");
//		DTList = daoIns.getContents();
//		for (int i = 0; i < DTList.size(); i++) {
//			tableModel.addRow(DTList.get(i));
//		}
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void EastSetting() {
		ep.setLayout(new BorderLayout());
		epNorth();
//		epCenter();
		epSouth();
		ep.add(midNp, "North");
		ep.add(midSp, "South");
	}

	private void epNorth() {
		midNp = new JPanel();
		midNp.setLayout(new BorderLayout());
		smNp = new JPanel();
		smNpSetting();
		smCp = new JPanel();
		smCpSetting();
		smSp = new JPanel();
		smSpSetting();
		midNp.add(smNp, "North");
		midNp.add(smCp, "Center");
		midNp.add(smSp, "South");
	}

	private void smSpSetting() {
		smSp.setLayout(new BorderLayout());
		smSnp = new JPanel();
		signIn = new JButton("로그인");
		signInAction();
		signUp = new JButton("회원가입");
		signUpAction();
		smSnp.add(signIn);
		smSnp.add(signUp);
		smSsp = new JPanel();
		loginMSG = new JLabel("");
		smSsp.add(loginMSG);
		smSp.add(smSnp, "North");
		smSp.add(smSsp, "Center");
	}

	private void smCpSetting() {
		pw = new JLabel("PW : ");
		PW = new JPasswordField(10);
		PW.setEchoChar('*');
		smCp.add(pw);
		smCp.add(PW);
	}

	private void smNpSetting() {
		id = new JLabel("   ID : ");
		ID = new JTextField(10);
		smNp.add(id);
		smNp.add(ID);
	}

	private void loginedEpNorth(String id) {
		midNplg = new JPanel();
		midNplg.setLayout(new BorderLayout());
		JPanel midNplgN = new JPanel();
		JLabel guest = new JLabel(id + " 님");
		midNplgN.add(guest);
		JPanel midNplgS = new JPanel();
		myinfo = new JButton("내 정보");
		logout = new JButton("로그아웃");
		midNplgS.add(myinfo);
		midNplgS.add(logout);
		midNplg.add(midNplgN, "North");
		midNplg.add(midNplgS, "Center");
		ep.add(midNplg, "North");
	}

	private void signInAction() {
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		ID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		PW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}

	private void login() {
		// ID/Pw일치할때만 로그인 성공이 나오게 하고 그외에는 전부
		// 가입되지 않은 ID이거나 ID/PW가 틀립니다.
		// 각 ID나 PW일치시 알려주는건 보안문제가 발생하니까
		String UIid = ID.getText();
		String UIpw = PW.getText();
		boolean loginCK = daoIns.login(UIid, UIpw);
		if (loginCK) {
			loginMSG.setText("");
			midNp.setVisible(false);
			loginedEpNorth(UIid);
		} else {
			loginMSG.setText("");
			loginMSG.setText("가입되지 않은 ID이거나 ID/PW가 틀립니다.");
		}
	}

	private void signUpAction() {
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderMSG = "signup";
//				Cct.send(orderMSG);//서버에게 회원가입 요청
				orderMSG = "";
			}
		});
	}

	private void epCenter() {
		// 노래클릭시 재생목록을 넣는 곳
	}

	private void epSouth() {
		midSp = new JPanel();
		midSp.setLayout(new BorderLayout());
		suggest = new JButton("오늘의 추천곡");
		listen = new JButton("선택한 음악 듣기");
		midSp.add(suggest, "North");
		midSp.add(listen, "South");
	}
}