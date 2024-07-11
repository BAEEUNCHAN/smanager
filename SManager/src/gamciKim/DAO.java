package gamciKim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * DB connection 기능.
 */
public class DAO {
	 Connection conn = null;
	 Statement stmt;
	 PreparedStatement psmt;
	 ResultSet rs;
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 접속 가능한 주소
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass); // db에 접속하는 역할
		 } catch (SQLException | ClassNotFoundException e) {
				System.out.println("오라클 드라이버 없음");
			e.printStackTrace();
		}
		return conn;
	}

}
