package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Smain {

	public static void main(String[] args) throws Exception {
		ServerSocket ssc = new ServerSocket();
		ssc.bind(new InetSocketAddress("10.0.0.97", 9999));
		while (true) {
			System.out.println("클라이언트 접속대기중");//확인용
			Socket sc = ssc.accept();
			System.out.println(sc.getInetAddress() + "/" + sc.getPort() + "/ 클라이언트 접속");// 접속확인용
			new SCenter(sc);
		}
	}
}