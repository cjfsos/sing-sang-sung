package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	public static DAO ins;
	private Connection con;
	private Statement st;
	private ResultSet rs;

	private DAO() {
	}

	public static DAO getInstance() {
		if (ins == null) {
			ins = new DAO();
		}
		return ins;
	}

	private boolean link() {
		boolean result = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String[]> getContents() {
		ArrayList<String[]> dList = new ArrayList<>();
		if (link()) {// if에서 link()를 한번 실행해줌
			try {
				st = con.createStatement();
				String sql = "select * from song";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Song_DTO Sdb = new Song_DTO();
					Sdb.setNo(rs.getInt("no"));
					Sdb.setStitle(rs.getString("곡명"));
					Sdb.setSinger(rs.getString("가수명"));
					Sdb.setAlbum(rs.getString("앨범"));
					Sdb.setGenre(rs.getString("장르"));
					dList.add(Sdb.getArray());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dList;
	}

	public void Idcheck() {
		ArrayList<String[]> idList = new ArrayList<>();
		if(link()) {
			try {
				st = con.createStatement();
				String sql = "select * from loogin where id='";//입력값을 넣자 직접넣음? preparestatemant
				rs = st.executeQuery(sql);
				while(rs.next()) {
					rs.getString("id");
					rs.getString("pw");							
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}