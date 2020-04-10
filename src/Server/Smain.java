package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Smain {

	public static void main(String[] args) throws Exception {
		ServerSocket ssc = new ServerSocket();
		ssc.bind(new InetSocketAddress("10.0.0.97", 9999));
		while(true) {
			Socket sc = ssc.accept();
//			Scenter st =new Scenter(sc);
//			st.start();
			new CenterS(sc);
		}
	}
}