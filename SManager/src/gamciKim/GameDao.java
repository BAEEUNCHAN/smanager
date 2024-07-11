package gamciKim;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

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
		conn = getConn();
		try {
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

	// 친구목록 조회 기능
	public List<GameVO> selectList(String id) {
		String sql = "select * ";
			   sql+= "from friend_info ";
			   sql+= "where game_id = ? ";
		List<GameVO> list = new ArrayList<>();

		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				GameVO gvo = new GameVO();
				gvo.setGame_server(rs.getString("game_server"));
				gvo.setNickname(rs.getString("nickname"));
				gvo.setGame_level(rs.getInt("game_level"));
				gvo.setGame_jobs(rs.getString("game_jobs"));
				gvo.setLog_status(rs.getString("log_status"));
				gvo.setCrt_location(rs.getString("crt_logation"));

				list.add(gvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
} // 클래스
