package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AppTest {
	public static Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 접속 가능한 주소
		String driver = "oracle.jdbc.driver.oracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	} // end of getConn

	public static void main(String[] args) {
		// 1) Oracle JDBC Driver 자바라이브러리
		// 2) Connection 객체
		// 조회기능
		Scanner sc = new Scanner(System.in);
		System.out.println(">학번 입력");
		String sno = sc.nextLine();
		System.out.println(">이름 입력");
		String sname = sc.nextLine();
		System.out.println(">연락처 입력");
		String phon = sc.nextLine();
		addStudent(sno,sname,phon); //입력
		search(); // 목록 조회 

//		System.out.println(">주소 수정");
//		String adr = sc.nextLine();
//		System.out.println(">학번 수정");
//		String stN = sc.nextLine();
//		modStudent(adr, stN);
		System.out.println(">정보 삭제");
		String phon = sc.nextLine();
		removeStudent(phon);
	}
	// 삭제기능.
	public static void removeStudent(String phone) {
		Connection conn = getConn();
		String sql = "DELETE FROM tbl_student ";
		sql += "WHERE std_phone = '"+phone+"'"; 
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수
			System.out.println("처리된 건수는 " + r + "건.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 수정기능 .
	public static void modStudent(String address, String stu_no) {
		Connection conn = getConn();
		String sql = "UPDATE tbl_student ";
		sql += "SET address= '" + address + "' WHERE std_no= '" + stu_no + "'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수
			System.out.println("처리된 건수는 " + r + "건.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 입력기능.
	// insert into student(std_no, std_name, std_phone)
	// values ('s1999-01','나는 오늘도 눈물을흘린다','010-1818-2828');
	public static void addStudent(String stuNo, String stdName, String phone) {
		Connection conn = getConn();
		String sql = "insert into tbl_student(std_no, std_name, std_phone)";
		sql += "values('" + stuNo + "','" + stdName + "','" + phone + "')"; // 변수 누적
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수
			System.out.println("처리된 건수는 " + r + "건.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 조회기능.
	public static void search() {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_student");
			// [객체1, 객체2, 객체3]
			while (rs.next()) {
				System.out.println(rs.getString("std_no") + ", " + rs.getString("std_name") + ", "
						+ rs.getString("std_phone") + rs.getString("address"));
			}
			System.out.println("end of data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회기능 끝.

	} 
}
