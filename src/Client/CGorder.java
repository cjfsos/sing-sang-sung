package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import JTable.Sign_Up;

public class CGorder extends Thread {
	InputStream IS = null;
	OutputStream OS = null;
	Socket sc;
	Sign_Up SU;

	CGorder(Socket sc) {
		this.sc = sc;
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
						byte reBuffer[] = new byte[1024];
						IS.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						System.out.println(sc.getInetAddress() + "/" + sc.getPort() + "/" + msg + "세번째");
						differ(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	protected void differ(String msg) {
		System.out.println(msg);
		if (msg.equals("signup_permit")) {
			SU = new Sign_Up(this);
		} else if (msg.equals("중복된 ID입니다.")) {
			SU.idcheck.setText("중복된 ID입니다.");
		} else if (msg.equals("사용가능한 ID입니다.")) {
			SU.idcheck.setText("사용가능한 ID입니다.");
		}
	}

	public void send(String msg) {
		try {
			OS = sc.getOutputStream();
			OS.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}