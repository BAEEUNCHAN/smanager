package gamciKim;

import java.util.List;
import java.util.Scanner;

import co.yedam.vo.StudentVO;

public class GameControl {
	Scanner sc = new Scanner(System.in);
	GameDao gdao = new GameDao();
	String logId = "";

	public void selectGameid() {
		while (true) {
			System.out.println("안녕하세요 모험가님! 오늘도 모험을 떠나볼까요?");
			System.out.println("아이디 :   ");
			String id = sc.nextLine();
			System.out.println("비밀번호 :  ");
			String pw = sc.nextLine();
			GameVO gvo = new GameVO();
			gvo.setGame_id(id);
			gvo.setGame_pwd(pw);
			gvo.getNickname();
			if (gdao.selectGameid(gvo) != null) {
				logId = id;
				System.out.println();
				System.out.println(gvo.getNickname() + "님 접속을 환영합니다");
				return;
			} else {
				System.out.println("아이디와 비밀번호를 확인해주세요.");
			}
		}
	}

	public void run() {
//		selectGameid();
		boolean isTrue = true;

		while (isTrue) {
			System.out.println("1.내정보 확인 2.친구 목록 3.친구 추가 4. 쪽지확인 5. 공지사항 6.로그아웃");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				selectList();
				break;

			case 2:

			}// switch 종료

		} // while 종료

	}// 메인 종료

	// 친구정보 조회
	void selectList() {
		List<GameVO> students = gdao.selectList(logId);
		System.out.println("닉네임 레벨 직업 현재상태 현재위치 새로운 쪽지건수");
		System.out.println("---------------------------");
		for (GameVO gvo : students) {
			System.out.println(gvo.briefShow());
		}
	}
}// 클래스 종료
