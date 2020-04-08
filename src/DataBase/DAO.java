package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	public boolean Idcheck(String id) {
		if (link()) {
			try {
				String sql = "select count(*) cnt from loogin where id= ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, id);
				rs = pst.executeQuery();

				if (rs.next()) {
					int cnt = rs.getInt("cnt");
					if (cnt > 0) {
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int signup(String id, String pw) {
		int k = 0;
		if (link()) {
			String sql = "insert into loogin values(?,?)";
			try {
				PreparedStatement pps = con.prepareStatement(sql);
				pps.setString(1, id);
				pps.setString(2, pw);
				k = pps.executeUpdate();
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return k;
	}

	public boolean login(String id, String pw) {
		if (link()) {
			try {
				String sql = "select count(*) cnt from loogin where id= ? and pw= ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, id);
				pst.setString(2, pw);
				rs = pst.executeQuery();

				if (rs.next()) {
					int cnt = rs.getInt("cnt");
					if (cnt > 0) {
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}