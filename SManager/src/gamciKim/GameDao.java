package gamciKim;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

/* 내정보, 친구목록, 친구추가, 쪽지확인, 공지사항/ 
 * 
 */

public class GameDao<messeageVO> extends DAO {

	// 아이디 확인
	public GameVO selectGameid(GameVO gLogin) {
		String sql = "select *";
		sql += "     from  my_info";
		sql += " where game_id = ?";
		sql += " and  game_pwd = ?";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, gLogin.getGame_id());
			psmt.setString(2, gLogin.getGame_pwd());
			rs = psmt.executeQuery();
			if (rs.next()) {
				gLogin.setNickname(rs.getString("nickname"));
				return gLogin;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} // selectGameid 종료

	// 내정보 확인 기능
	public GameVO myinfoSelect(GameVO gLogin1) {
		String sql = "select nickname, ";
		sql += "		game_level, ";
		sql += "        game_jobs, ";
		sql += "        log_status, ";
		sql += "       crt_location,";
		sql += "         n_mail ";
		sql += "from     my_info ";
		sql += "where game_id = ?";
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, gLogin1.getGame_id());
			rs = psmt.executeQuery();
			if (rs.next()) {
				gLogin1.setNickname(rs.getString("nickname"));
				gLogin1.setGame_level(rs.getInt("game_level"));
				gLogin1.setGame_jobs(rs.getString("game_jobs"));
				gLogin1.setLog_status(rs.getString("log_status"));
				gLogin1.setCrt_location(rs.getString("crt_location"));
				gLogin1.setN_mail(rs.getInt("n_mail"));
				return gLogin1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	} // myinfoSelect 종료

	// 친구 목록 확인 기능
	public List<GameVO> friendInfo(GameVO gLogin2) {
		String sql = "select m.game_server, ";
		sql += "m.nickname, ";
		sql += "m.log_status, ";
		sql += "m.crt_location ";
		sql += "from friend_info f ";
		sql += "join my_info m ";
		sql += "on f.friend_id = m.game_id ";
		sql += "where f.game_id = ?";
		List<GameVO> list = new ArrayList<>();
		conn = getConn();
		try {
			rs = psmt.executeQuery(sql);
			psmt.setString(1, gLogin2.getGame_id());
			rs = psmt.executeQuery();

			while (rs.next()) {
				GameVO gvo = new GameVO();
				gvo.setGame_server(rs.getString("Game_server"));
				gvo.setNickname(rs.getString("Nickname"));
				gvo.setLog_status(rs.getString("Log_status"));
				gvo.setCrt_location(rs.getString("Crt_location"));

				list.add(gvo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // 친구목록 확인기능 종료

	// 친구 추가
	public boolean insertFri(GameVO gvo) {
		String selectSql = "select game_id, " + "game_server " + "from my_info " + "where game_id = ? "
				+ "and game_server = ?";

		String insertSql = "insert into friend_info (game_id, friend_id) " + "values (?, ?)";
		conn = getConn();
		try {
			psmt = conn.prepareStatement(selectSql);
			psmt.setString(1, gvo.getFriend_id());
			psmt.setString(2, gvo.getGame_server());
			rs = psmt.executeQuery();

			if (rs.next()) {
				psmt = conn.prepareStatement(insertSql);
				psmt.setString(1, gvo.getGame_id());
				psmt.setString(2, gvo.getFriend_id());
				int r = psmt.executeUpdate(); // 쿼리 실행
				if (r == 1) {
					return true; // 정상처리.
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리.

	} // 친구 추가 종료

	// 쪽지 확인
	public List<GameVO> messeageList() {
		String sql = "SELECT mail_num, to_id, from_id, mail_title, mail_view, sys_date "
				    + "FROM note_mail"; // 실제 테이블
		List<GameVO> list = new ArrayList<>();
																												
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) { // 한 번만 실행되도록 if 문으로 변경
	            GameVO mvo = new GameVO();
	            mvo.setN_mail(1);
	            mvo.setMail_num(1);
	            mvo.setTo_id("펜리르(앙예링)");
	            mvo.setFrom_id("앙예링");
	            mvo.setMail_title("조졌다.");
	            mvo.setMail_view("ㄴ r는 오늘도 눈물을 흘린ㄷr ...★\n"
	                             + "가끔은 눈물을 참을 수 없는 \n"
	                             + "ㄴ ㅐㄱ r 별루 ㄷr ......★\n"
	                             + "하지만 울 줄 안다는건 조은거ㅇF\n"
	                             + "나 ㅈr신에게 솔직할 수 있는거잖ㅇr...★");
	            mvo.setSys_date("2024-07-10");
	            
	            list.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;
	}
}// 클래스
