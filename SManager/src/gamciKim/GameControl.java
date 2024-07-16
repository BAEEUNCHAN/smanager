package gamciKim;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameControl {
	Scanner sc = new Scanner(System.in);
	GameDao gdao = new GameDao();
	String logId = "";

	public void selectGameid() {
		while (true) {
			System.out.println("Final fate Online");
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
				System.out.println();
				return;
			} else {
				System.out.println("아이디와 비밀번호를 확인해주세요.");
			}
		}
	} // selectGameid();

	public void run() {
		boolean isTrue = true;
		while (isTrue) {
			System.out.println("1.내정보 확인 2.친구 목록 3.친구 추가 4. 쪽지 확인 5. 공지사항 6. 로그아웃");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				myinfoSelect();
				break;
			case 2:
				friendInfo();
				break;
			case 3:
				insertFri();
				break;
			case 4:
				messeageList();
				break;
			case 5:
				noticeList();
				break;
			case 6:
				System.out.println();
				System.out.println("[오늘 모험도 정말 멋졌어요! 다음에 또 만나요~!]");
				isTrue = false;
				break;

			default:
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				break;

			}
		} // switch 종료

	} // while 종료

	// 내정보 조회
	void myinfoSelect() {
		GameVO gvo = new GameVO();
		gvo.setGame_id(logId); // 로그인한 사용자의 아이디를 설정
		gvo = gdao.myinfoSelect(gvo); // 데이터베이스에서 사용자 정보 조회
		if (gvo != null) {
			System.out.println("닉네임 : " + gvo.getNickname());
			System.out.println("레벨 : " + gvo.getGame_level());
			System.out.println("직업 : " + gvo.getGame_jobs());
			System.out.println("현재 상태 :" + gvo.getLog_status());
			System.out.println("현재 위치 :" + gvo.getCrt_location());
			System.out.println("새로운 쪽지 건수 :" + gvo.getN_mail());
			System.out.println();
			return;
		} else {
			System.out.println("접속 여부를 확인해주세요");

		}

	} // 내정보 조회 종료

	// 친구목록 조회
	void friendInfo() {
		GameVO friend_Id = new GameVO();
		friend_Id.setGame_id(logId);
		List<GameVO> Games = gdao.friendInfo(friend_Id);
		System.out.println("===============================================================");
		System.out.println(String.format("%-10s %-15s %-15s %-20s", "서버", "닉네임", "현재 상태", "현재 위치"));
		System.out.println("===============================================================");
		for (GameVO gvo : Games) {
			System.out.println(gvo.briefShow());
		}
		System.out.println();
	} // 친구목록 조회 종료

	// 친구 추가
	void insertFri() {
		System.out.println("아이디 입력 >");
		String fid = sc.nextLine();
		System.out.println("서버 입력 >");
		String fsv = sc.nextLine();

		GameVO gvo = new GameVO();
		gvo.setFriend_id(fid);
		gvo.setGame_server(fsv);
		gvo.setGame_id(logId);
		if (gdao.insertFri(gvo)) {
			System.out.println("친구 추가가 완료되었습니다.");
		} else {
			System.out.println("입력 정보를 정확히 확인해주세요.");
			System.out.println();

		}
	}

	// 쪽지확인
	void messeageList() {
		GameVO mail_id = new GameVO();
		mail_id.setGame_id(logId);
		List<GameVO> messages = gdao.messeageList(mail_id);
		System.out.println("★신규 쪽지 총 " + messages.size() + "건");
		System.out.println("자세히 보기 y                    나가기 n");
		System.out.println("--------------------------------------------------------------------------");
		System.out.print("선택 > ");
		String choice = sc.nextLine();

		switch (choice) {
		case "y":
			mail_view(messages);
			break;
		case "n":
			System.out.println("나가기");
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
			messeageList();
			break;
		}
	}

	// 쪽지 상세 보기
	void mail_view(List<GameVO> messages) {
		if (messages.isEmpty()) {
			System.out.println("[모든 쪽지를 확인 했습니다]");
			System.out.println();
			return;
		}

		for (GameVO message : messages) {
			System.out.println("쪽지번호 " + message.getMail_num() + "      작성자 " + message.getT_nick());
			System.out.println("제목: " + message.getMail_title());
			System.out.println(message.getMail_view());
			System.out.println();
			System.out.println("보낸 날짜: " + message.getSys_date());
			System.out.println("--------------------------------------------------------------------------");
		}
		System.out.println("[모든 쪽지를 확인 했습니다]");
	}

	// 공지사항 목록보기
	void noticeList() {
		boolean run1 = true;
		while (run1) {
			List<GameVO> notice = gdao.noticeList();
			Collections.reverse(notice); // 리스트를 역순으로 정렬
			System.out.println("[공지사항]");
			System.out.println("===========================================================================");
			System.out.println(String.format("%-10s %-20s %-25s %-20s", "번호", "제목", "업로드 날짜", "읽음"));
			System.out.println("===========================================================================");
			for (GameVO gvo : notice) {
				System.out.println(gvo.briefShow1());
				System.out.println();
			}
			System.out.println("===========================================================================");
			System.out.println("자세히 보려면 번호 입력, 나가기 n");
			System.out.print("선택 > ");

			String input = sc.nextLine();

			try {
				int noticeNum = Integer.parseInt(input);
				noticeView(noticeNum);

			} catch (Exception e) {
				if (input.equals("n")) {
					run1 = false;
					System.out.println("[선택화면으로 돌아갑니다.]");
				} else {
					System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				}
			}
		}
	} // 공지사항 목록보기 종료

	// 공지사항 자세히 보기
	void noticeView(int noticeNum) {
		GameVO gvo = gdao.noticeview(noticeNum);
		System.out.println( "번호 " + gvo.getNotice_num()+ " | "+"★제목  " + gvo.getNotice_title() + " | " + "읽음 여부 " + gvo.getRead_notice());
		System.out.println("=========================================================================================================");
		System.out.println(gvo.getNotice_view());
		System.out.println();
		System.out.println("업로드 날짜 " + gvo.getUp_date().substring(0,10));
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
	} // 공지사항 자세히 보기

}// GameControl
