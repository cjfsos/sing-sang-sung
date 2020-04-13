package Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Sorder extends Thread {
	Socket sc;
	InputStream IS = null;
	
	Sorder(Socket S) {
		sc = S;
	}

	@Override
	public void run() {
		receive();
		send();
	}

	private void receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						IS = sc.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void send() {

	}
}