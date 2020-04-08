package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import JTable.MainFrame;
import JTable.Sign_Up2;

public class Ccenter{
	private Socket sc = null;
	private InputStream rcMsg = null;
	private OutputStream sdMsg = null;
	private Sign_Up2 Sign_UpIp = null;

	Ccenter(Socket sc) {
		this.sc = sc;
		new MainFrame(this);
		while (true) {
			try {
				rcMsg = sc.getInputStream();
				byte reBuffer[] = new byte[100];
				rcMsg.read(reBuffer);
				String msg = new String(reBuffer);
				msg = msg.trim();
				group(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void group(String msg) {
		System.out.println(msg);
		switch (msg) {
		case "signup_assent":
			Sign_UpIp = new Sign_Up2(this);
			break;
		case "중복된 ID":
			Sign_UpIp.idcheck.setText("중복된 ID입니다.");
			Sign_UpIp.btnId.setText("ID중복확인");
			break;
		case "사용가능한 ID":
			Sign_UpIp.idcheck.setText("사용가능한 ID입니다.");
			Sign_UpIp.btnId.setText("사용가능");
			break;
		}
	}

	public void send(String msg) {
		try {
			sdMsg = sc.getOutputStream();
			sdMsg.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}