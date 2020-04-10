package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import DataBase.Song_DTO;
import JTable.MainFrame;
import JTable.Sign_Up;

public class Ccenter {
	private Socket sc = null;
	private InputStream rcMsg = null;
	private OutputStream sdMsg = null;
	private MainFrame MF = null;
	private Sign_Up Sign_UpIp = null;

	Ccenter(Socket sc) {
		this.sc = sc;
		while (true) {
			try {
				rcMsg = sc.getInputStream();
				byte reBuffer[] = new byte[100];
//				rcMsg.read(reBuffer, 0, reBuffer.length);
				rcMsg.read(reBuffer);
				String msg = new String(reBuffer);
				msg = msg.trim();
				group(msg, reBuffer);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void group(String msg, byte[] reBuffer) {
		if (msg.equals("연결되었습니다.")) {
			MF = new MainFrame(this);
		} else if (msg.equals("signup_assent")) {
			Sign_UpIp = new Sign_Up(this);
		} else if (msg.equals("중복된 ID")) {
			Sign_UpIp.idcheck.setText("중복된 ID입니다.");
			Sign_UpIp.btnId.setText("ID중복확인");
		} else if (msg.equals("사용가능한 ID")) {
			Sign_UpIp.idcheck.setText("사용가능한 ID입니다.");
			Sign_UpIp.btnId.setText("사용가능");
		} else {
			ObjectReceive(reBuffer);
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

	private void ObjectReceive(byte[] reBuffer) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);
			ObjectInputStream ois = new ObjectInputStream(bais);
			try {
				Object o = ois.readObject();
				Song_DTO sd = (Song_DTO) o;
				System.out.println(sd.getStitle());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}