package JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Client.CCenter;
import Client.CObject;
import DataBase.DAO;

public class MainFrame extends JFrame {
	String header[] = { "번호", "곡명", "가수명", "앨범", "장르" };
	String contents[][];
	String SList[] = { "test1", "test2", "test3" };
	public DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	JTable table = new JTable(tableModel);
	JScrollPane Scroll = new JScrollPane(table);
	JPanel wp = new JPanel();
	JPanel ep = new JPanel();
	JLabel id;
	JLabel pw;
	public JTextField ID;
	public JPasswordField PW;
	JButton signIn;
	public JButton signUp;
	public JPanel midNp = null;
	JPanel smNp = null;
	JPanel smCp = null;
	JPanel smSp = null;
	JPanel smSsp = null;
	JPanel smSnp = null;
	public JLabel loginMSG = null;
	JPanel midCT = null;
	JPanel midSp = null;
	JButton suggest = null;
	JButton listen = null;
	JPanel midNplg = null;
	JButton myinfo = null;
	JButton logout = null;
	DAO daoIns = DAO.getInstance();
	ArrayList<String[]> DTList = new ArrayList<>();
	String orderMSG;
	CObject CO;
	public CCenter CT;
	MainFrame MF = this;
	public Sign_Up SU = null;
	public String UIid;
	String UIpw;
	private JPanel MainPane;
	int selectedRow = -1;
	MsgBox MB = new MsgBox();

	public MainFrame(CCenter CT, CObject CO) {
		this.CT = CT;
		this.CO = CO;
		CO.setMFins(MF);
//		System.out.println(this.CT + "/" + CT + "/" + this.CO + "/" + CO);//확인용
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPane);
		this.setBounds(200, 150, 800, 600);
		this.setSize(new Dimension(860, 655));
		WestSetting();
		EastSetting();
		MainPane.setLayout(null);
		Scroll.setBounds(0, 0, 639, 6178);
		getContentPane().add(Scroll);
		ep.setBounds(638, 0, 211, 620);
		getContentPane().add(ep);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	private void WestSetting() {
		String loadList = "SongList";
		CO.ReceiveNewSongList();// 리스트를 오브젝트로 받을 준비
		CT.Send(loadList);// 리스트를 서버에게 달라고 요청
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void EastSetting() {
		ep.setLayout(new BorderLayout());
		epNorth();
		epCenter();
		epSouth();
		ep.add(midNp, "North");
		ep.add(midCT, "Center");
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

	public void loginedEpNorth(String id) {
		midNplg = new JPanel();
		midNplg.setLayout(new BorderLayout());
		JPanel midNplgN = new JPanel();
		JLabel guest = new JLabel(id + " 님");
		midNplgN.add(guest);
		JPanel midNplgS = new JPanel();
		myinfo = new JButton("내 정보");
		logout = new JButton("로그아웃");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				midNplg.setVisible(false);
				midNp.setVisible(true);
			}
		});
		midNplgS.add(myinfo);
		midNplgS.add(logout);
		midNplg.add(midNplgN, "North");
		midNplg.add(midNplgS, "Center");
		ep.add(midNplg, "North");
	}

	private void signInAction() {
		signIn.addActionListener(new ActionListener() {// 로그인 버튼을 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		ID.addActionListener(new ActionListener() {// ID텍스트필드에서 엔터를 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		PW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// PW텍스트필드에서 엔터를 눌렀을때
				login();
			}
		});
	}

	private void login() {
		// ID/Pw일치할때만 로그인 성공이 나오게 하고 그외에는 전부
		// 가입되지 않은 ID이거나 ID/PW가 틀립니다.
		// 각 ID나 PW일치시 알려주는건 보안문제가 발생하니까
		if (!ID.getText().equals("") && !PW.getText().equals("")) {
			UIid = ID.getText();
			UIpw = PW.getText();
			String loginCKmsg = "loginChecking/" + UIid + "/" + UIpw;
			CT.Send(loginCKmsg);// 바로 메시지 보내지 말고 입력값이 없으면 클라이언트에서 바로 오류메시지 뜨게할것!
		} else {
			loginMSG.setText("ID와 PW를 전부 입력해주세요.");
		}
	}

	private void signUpAction() {
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SU = new Sign_Up(MF);// 이 매개변수에서 this를 쓰면 오버라이드된 actionPerformed를 매개변수가된다.
			}
		});
	}

	private void epCenter() {
		midCT = new JPanel();
		JList SBList = new JList(SList);
		JScrollPane JP = new JScrollPane(SBList);
		JP.setPreferredSize(new Dimension(200, 450));
		midCT.add(JP);
		midCT.setSize(200, 200);
		setVisible(true);
		// 노래클릭시 재생목록을 넣는 곳
	}

	private void epSouth() {
		midSp = new JPanel();
		midSp.setLayout(new BorderLayout());
		suggest = new JButton("오늘의 추천곡");
		listen = new JButton("선택한 음악 듣기");
		listen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {// 행이 선택되지 않은 상태
					MB.logNotSel();
				}
//					else if() {// 로그인된상태+행이 선택된 상태
//					
//				}else if() {// 로그인이 안된상태+행이 선택된 상태
//					
//				}
				
				
			}
		});
		midSp.add(suggest, "North");
		midSp.add(listen, "South");
	}
}