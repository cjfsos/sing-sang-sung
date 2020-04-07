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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DataBase.DAO;

public class MainFrame extends JFrame {
	String header[] = { "번호", "곡명", "가수명", "앨범", "장르" };
	String contents[][];
	DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	JTable table = new JTable(tableModel);
	JScrollPane Scroll = new JScrollPane(table);
	JPanel wp = new JPanel();
	JPanel ep = new JPanel();
	JLabel id;
	JLabel pw;
	JTextField ID;
	JTextField PW;
	JButton signIn;
	JButton signUp;
	JPanel midNp = null;
	JPanel smNp = null;
	JPanel smCp = null;
	JPanel smSp = null;
	JPanel midSp = null;
	JButton suggest = null;
	JButton listen = null;
	DAO daoIns = DAO.getInstance();
	ArrayList<String[]> DTList = new ArrayList<>();

	public MainFrame() {
		Dimension size = new Dimension(700, 500);
		this.setSize(size);
		WestSetting();
		EastSetting();
		this.add(Scroll);
		this.add(ep, "East");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	private void WestSetting() {
		DTList = daoIns.getContents();
		for(int i = 0; i < DTList.size() ;i++) {
			tableModel.addRow(DTList.get(i));
		}
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
		smCp = new JPanel();
		smSp = new JPanel();
		id = new JLabel("   ID : ");
		ID = new JTextField(10);
		smNp.add(id);
		smNp.add(ID);
		pw = new JLabel("PW : ");
		PW = new JTextField(10);
		smCp.add(pw);
		smCp.add(PW);
		signIn = new JButton("로그인");
		signUp = new JButton("회원가입");
		signUpAction();
		
		smSp.add(signIn);
		smSp.add(signUp);
		midNp.add(smNp, "North");
		midNp.add(smCp, "Center");
		midNp.add(smSp, "South");
	}

	private void signUpAction() {
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object act = e.getSource();
				if(act.equals(signUp)) {
					new Signup();
				}
			}
		});
	}

	private void epCenter() {
		//노래클릭시 재생목록을 넣는 곳
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