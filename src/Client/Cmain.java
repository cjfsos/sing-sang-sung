package Client;

import java.io.IOException;
import java.net.Socket;

import JTable.MainFrame;

public class Cmain {

	public static void main(String[] args) throws Exception, IOException {
		Socket sc = new Socket("10.0.0.97", 9999);
		new Ccenter(sc);
	}
}