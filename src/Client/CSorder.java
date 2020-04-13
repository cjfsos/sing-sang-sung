package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class CSorder extends Thread{
	InputStream IS = null;
	Socket sc;

	CSorder(Socket sc) {
		this.sc = sc;
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
				try {
					IS = sc.getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void send() {
		
	}
}