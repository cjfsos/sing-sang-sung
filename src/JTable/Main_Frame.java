package JTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DataBase.DAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Main_Frame extends JFrame {

	private JPanel contentPane;
	private String header[] = { "번호", "곡명", "가수명", "앨범", "장르" };
	private String contents[][] = { {} };
	private DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	private JTable table = new JTable(tableModel);
	private JScrollPane scrollPane = new JScrollPane(table);
	private ArrayList<String[]> DTList = new ArrayList<>();
	DAO daoIns = DAO.getInstance();
	private final JLabel lblNewLabel_1 = new JLabel("ID");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Frame() {
		Center();
	}

	private void Center() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DTList = daoIns.getContents();
		for(int i = 0; i < DTList.size() ;i++) {
			tableModel.addRow(DTList.get(i));
		}
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 510, 472);
		contentPane.add(scrollPane);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lblNewLabel_1.setBounds(522, 10, 57, 15);
		
		contentPane.add(lblNewLabel_1);
		this.setResizable(false);
	}
}
