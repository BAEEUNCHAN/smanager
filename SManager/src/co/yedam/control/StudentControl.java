package co.yedam.control;

import java.util.List;
import java.util.Scanner;

import co.yedam.common.StudentDAO;
import co.yedam.vo.StudentVO;

/*
 *  사용자 입력을 가이드, 처리된 결과 출력
 */

public class StudentControl {
	Scanner sc = new Scanner(System.in);
	StudentDAO sdao = new StudentDAO();

	public void run() {
		boolean isTrue = true;

		while (isTrue) {
			System.out.println("1.학생목록 2.등록 3. 수정 4. 삭제 5. 종료");
			System.out.print("선택> ");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				studentList();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				modifyStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				System.out.println("종료");
				isTrue = false;
			}

		}
	} // end of run().

	// 삭제 기능
	void deleteStudent() {
		System.out.println("삭제할 학생 번호 입력 > ");
		String stuN = sc.nextLine();

		if (sdao.deleteStudent(stuN)) {
			System.out.println("[처리되었습니다.]");
		} else {
			System.out.println("[삭제 처리가 되지 않았습니다.]");
		}

	}

	// 수정기능.
	void modifyStudent() {
		String sno = ""; // 블럭 레벨 변수

		while (true) {
			System.out.println("변경할 학생번호를 입력하세요 > ");
			sno = sc.nextLine();
			if (sdao.selectExists(sno) == 1) {
				// 학생번호 존재.
				break;
			}
			System.out.println("[확인된 번호가 없습니다. 다시 입력 해주세요 >]");
		}
		System.out.println("변경할 연락처를 입력하세요 > ");
		String phone = sc.nextLine();
		System.out.println("변경할 주소를 입력하세요 > ");
		String addr = sc.nextLine();

		StudentVO svo = new StudentVO();
		svo.setStdNo(sno);
		svo.setStdPhone(phone);
		svo.setAddress(addr);

		if (sdao.updateStudent(svo)) {
			System.out.println("[수정완료]");
		}
	}

	// 등록 기능
	void addStudent() {
		System.out.println(">학번 입력");
		String sno = sc.nextLine();
		System.out.println(">이름 입력");
		String sname = sc.nextLine();
		System.out.println(">연락처 입력");
		String phon = sc.nextLine();
		System.out.println(">주소 입력");
		String addr = sc.nextLine();
		System.out.println(">생일 입력");
		String bir = sc.nextLine();

		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(phon);
		std.setAddress(addr);
		std.setBirthdate(bir);

	}

	// 목록 출력 기능.
	void studentList() {
		List<StudentVO> students = sdao.selectList();
		System.out.println("학생번호 학생이름 연락처 ");
		System.out.println("---------------------------");
		for (StudentVO svo : students) {
			System.out.println(svo.briefShow());
		}
	} // end of studentList().

}
