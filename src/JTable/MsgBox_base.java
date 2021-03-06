package JTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MsgBox_base extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MsgBox frame = new MsgBox();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MsgBox_base() {
		super("안내메시지");
	}

	public void signupMSG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 177);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("<html><body>sing-sang-sung에<br>가입 하신걸 환영합니다.</body></html>");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 10, 217, 37);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인!");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\회원가입 완료 버튼.jpg"));
		btnNewButton.setBounds(78, 73, 78, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\회원가입 배경.png"));
		lblNewLabel_1.setBounds(0, 0, 230, 140);
		contentPane.add(lblNewLabel_1);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public void signupFalseMSG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 177);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ID중복확인과 PW 사용가능 여부가");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(12, 10, 208, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("전부 확인 된 후 회원가입이");
		label.setFont(new Font("굴림", Font.PLAIN, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 31, 208, 24);
		contentPane.add(label);
		
		JButton btnNewButton_1 = new JButton("확인");
		btnNewButton_1.setBounds(60, 90, 105, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel(" 가능합니다.");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(12, 52, 208, 15);
		contentPane.add(lblNewLabel_3);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
