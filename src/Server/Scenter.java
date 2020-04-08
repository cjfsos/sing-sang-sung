package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import DataBase.DAO;

public class Scenter {
	private Socket sc = null;
	private InputStream rcMsg = null;
	private OutputStream sdMsg = null;
	private DAO daoIns = DAO.getInstance();

	Scenter(Socket sc) {
		this.sc = sc;
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
		String sendMsg;
		if (msg.equals("signup")) {
			send("signup_assent");
		} else if (msg.contains("ID중복확인")) {
			System.out.println(msg);
			StringTokenizer tk = new StringTokenizer(msg, "/");
			tk.nextToken();
			String id = tk.nextToken();
			System.out.println(id);
			if (daoIns.Idcheck(id)) {
				sendMsg = "중복된 ID";
				send(sendMsg);
				sendMsg = "";
			} else {
				sendMsg = "사용가능한 ID";
				send(sendMsg);
				sendMsg = "";
			}
		}
	}

	private void send(String msg) {
		try {
			sdMsg = sc.getOutputStream();
			sdMsg.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}