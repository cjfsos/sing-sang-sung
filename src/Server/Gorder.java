package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import DataBase.DAO;

public class Gorder extends Thread {
	InputStream IS = null;
	OutputStream OS = null;
	Socket sc;
	DAO daoIn = DAO.getInstance();

	Gorder(Socket G) {
		sc = G;
	}

	@Override
	public void run() {
		receive();
	}

	private void receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						IS = sc.getInputStream();
						byte[] reBuffer = new byte[1024];
						IS.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						differ(msg);

					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}).start();

	}

	protected void differ(String msg) {
		if (msg.equals("signup")) {
			send("signup_permit");
		} else if (msg.contains("ID중복확인")) {
			StringTokenizer tk = new StringTokenizer(msg, "/");
			tk.nextToken();
			String id = tk.nextToken();
			if(daoIn.Idcheck(id)) {
				send("중복된 ID입니다.");
			}else{
				send("사용가능한 ID입니다.");
			}
		}
	}

	private void send(String msg) {
		try {
			OS = sc.getOutputStream();
			OS.write(msg.getBytes());
			System.out.println(sc.getInetAddress()+"/"+sc.getPort()+"/"+msg+"두번째");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}