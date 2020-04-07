package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Smain {

	public static void main(String[] args) throws Exception {
		ServerSocket ssc = new ServerSocket();
		ssc.bind(new InetSocketAddress("10.0.0.97",9999));
		Socket sc = null;
		while(true) {
			System.out.println("기다림!");
			sc = ssc.accept();
		}
	}
}