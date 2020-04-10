package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import DataBase.DAO;
import DataBase.Song_DTO;

public class Scenter extends Thread{
	private Socket sc = null;
	private InputStream rcMsg = null;
	private OutputStream sdMsg = null;
	private DAO daoIns = DAO.getInstance();

	Scenter(Socket sc) {
		this.sc = sc;
		System.out.println(sc.getInetAddress() + "/" + sc.getPort() + "님이 연결되었습니다.");
		send("연결되었습니다.");
	}

	@Override
	public void run() {
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
				System.out.println(sc.getInetAddress() + "/" + sc.getPort() + "님 연결이 끈어졌습니다.");
				break;
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
		}else if (msg.equals("need_SList_Data")) {
			daoIns.getContents(this);
		}
	}

	public void send(String msg) {
		try {
			sdMsg = sc.getOutputStream();
			sdMsg.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ObjectSend(Song_DTO ob) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bos);
			os.writeObject(ob);
			byte[] bowl = bos.toByteArray();
			sdMsg = sc.getOutputStream();
			sdMsg.write(bowl);
			os.close();
			System.out.println("직렬화가 끝났습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("오브젝트아웃풀예외처리");
		}
	}
}