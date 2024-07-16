package gamciKim;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/* 내정보, 친구목록, 친구추가, 쪽지확인, 공지사항/ 
 * 
 */

public class GameDao extends DAO {

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
			psmt = conn.prepareStatement(sql);
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
		String commit = "commit ";
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
	public List<GameVO> messeageList(GameVO gLogin3) {
		String sql = "select n.mail_num,";
		sql += "n.to_id,";
		sql += "(SELECT nickname FROM my_info WHERE n.to_id = game_id) as tnick,";
		sql += "n.from_id,";
		sql += "(SELECT nickname FROM my_info WHERE n.from_id = game_id) as fnick,";
		sql += "n.mail_title,";
		sql += "n.mail_view,";
		sql += "n.sys_date,";
		sql += "n.read_mail";
		sql += " from note_mail n";
		sql += " where n.from_id = ?"; // 쪽지 확인 테이블
		List<GameVO> list = new ArrayList<>();

		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, gLogin3.getGame_id());
			rs = psmt.executeQuery();
			if (rs.next()) {
				GameVO mvo = new GameVO();
				mvo.setMail_num(rs.getInt("mail_num"));
				mvo.setT_nick(rs.getString("tnick"));
				mvo.setFrom_id(rs.getString("from_id"));
				mvo.setMail_title(rs.getString("mail_title"));
				mvo.setMail_view("ㄴ r는 오늘도 눈물을 흘린ㄷr ...★\n" + "가끔은 눈물을 참을 수 없는 \n" + "ㄴ ㅐㄱ r 별루 ㄷr ......★\n"
						+ "하지만 울 줄 안다는건 조은거ㅇF\n" + "나 ㅈr신에게 솔직할 수 있는거잖ㅇr...★");
				mvo.setSys_date("2024-07-10");

				list.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;
	}

	// 공지사항 목록 보기
	public List<GameVO> noticeList() {
		String sql = "select * ";
		sql += "from notice ";
		List<GameVO> list = new ArrayList<>();
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) { // 공지 내용 구성
				GameVO mvo = new GameVO();
				mvo.setNotice_num(rs.getInt("notice_num"));
				mvo.setNotice_title(rs.getString("Notice_title"));
				mvo.setUp_date(rs.getString("Up_date"));
				mvo.setNotice_view(rs.getString("Notice_view"));
				mvo.setRead_notice(rs.getString("Read_notice"));

				list.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // 공지사항 목록 보기 종료

	// 공지사항 자세히 보기
	public GameVO noticeview(int gLogin4) {
		String sql = "select * ";
		sql += "from notice ";
		sql += "where notice_num = ?";
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gLogin4);
			rs = psmt.executeQuery();
			if (rs.next()) {
				GameVO gvo = new GameVO();
				gvo.setNotice_num(rs.getInt("notice_num"));
				gvo.setNotice_title(rs.getString("notice_title"));
				gvo.setNotice_view(rs.getString("notice_view"));
				gvo.setUp_date(rs.getString("up_date"));
				gvo.setRead_notice(rs.getString("Read_notice"));
				return gvo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}// 클래스
