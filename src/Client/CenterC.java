package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import JTable.MainFrame;

public class CenterC {
	private Socket sc = null;
	private InputStream inMsg = null;
	private OutputStream outMsg = null;
	private MainFrame MF = null;

	CenterC(Socket sc) {
		this.sc = sc;
		beginSet();
		beginData();
	}

	private void beginSet() {
		byte reBuffer[] = new byte[1024];
		try {
			outMsg = sc.getOutputStream();
			String begin = "StartProgram";
			outMsg.write(begin.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("송신 오류");
		}

		try {
			inMsg = sc.getInputStream();
			inMsg.read(reBuffer);
			String sdMsg = new String(reBuffer);
			sdMsg = sdMsg.trim();
			if (sdMsg.equals("allowProgram")) {
				MF = new MainFrame(this);
			} else {
				System.out.println("관리자에게 문의하세요.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("수신오류2");
		}
	}

	private void beginData() {
		byte[] reBuffer = new byte[1024];
		// byte타입으로 받을 그릇 생성
		try {
			inMsg = sc.getInputStream();
			// 소켓을 통해 받을 InputStream생성
			inMsg.read(reBuffer);
			// byte 그릇으로 전송된 Data를 읽어옴
			ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);
			// 해당 byte를 정해진 순서대로 조립하기 위함
			ObjectInputStream ois = new ObjectInputStream(bais);
			// bais의 순서대로 오브젝트 타입으로 byte를 재조립
			try {
				Object o = ois.readObject();
				// 정해진 순서대로 재조립된 byte를 Object객체로 형 변환
				ArrayList<String[]> Data = (ArrayList<String[]>) o;
				// Object인 o를 직렬화전의 객체 형태인 ArrayList<String[]>타입으로 재조직
				for (int i = 0; i < Data.size(); i++) {
					MF.tableModel.addRow(Data.get(i));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
