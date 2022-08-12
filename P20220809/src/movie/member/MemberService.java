package movie.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberService {
	public static Member memberInfo = null;
	Scanner scn = new Scanner(System.in);
	public MemberService() {
		
	}
	
	// 로그인
	public void loginId() {
		Member member = new Member();
		
		System.out.print("ID ▶ ");
		String id = scn.nextLine();
		System.out.print("PW ▶ ");
		String pw = scn.nextLine();
		
		member = MemberManage.getInstance().loginId(id);
		
		if(member.getMemberPw().equals(pw)) {
			memberInfo = member;
		} else {
			System.out.println("비밀번호를 잘못 입력했습니다.");
		}
	}
	
	// 로그아웃
	public void logoutId() {
		if(memberInfo!=null) {
			memberInfo=null;
		}
	}
	
	// 회원 등록
	public void registMember() {
		Member member = new Member();
		
		System.out.println("────────── 신규 회원 등록 ──────────");
		System.out.print("회원 ID ▶ ");
		String id = scn.nextLine();
		member.setMemberId(id);
		System.out.print("회원 PW ▶ ");
		String pw = scn.nextLine();
		member.setMemberPw(pw);
		System.out.print("회원 이름 ▶ ");
		String name = scn.nextLine();
		member.setMemberName(name);
		System.out.print("회원 연락처 ▶ ");
		String tel = scn.nextLine();
		member.setMemberTel(tel);
		int result = MemberManage.getInstance().registMember(member);
		if(result==1) {
			System.out.println("신규 회원이 등록되었습니다.");
		} else {
			System.out.println("ERROR!!(신규 회원 등록 실패)");
		}
	}
	
	// 회원 가입 - 회원용
	public void registMemberU() {
		Member member = new Member();
		
		System.out.println("<< 회원 가입 >>");
		System.out.print("ID ▶ ");
		String id = scn.nextLine();
		member.setMemberId(id);
		System.out.print("PW ▶ ");
		String pw = scn.nextLine();
		member.setMemberPw(pw);
		System.out.print("이름 ▶ ");
		String name = scn.nextLine();
		member.setMemberName(name);
		System.out.print("연락처 ▶ ");
		String tel = scn.nextLine();
		member.setMemberTel(tel);
		int result = MemberManage.getInstance().registMember(member);
		if(result==1) {
			System.out.println("┌────────────────────────────────────────┐");
			System.out.printf("  %5s 님 MOVIE APP 가입을 환영합니다! \n", name);
			System.out.println("└────────────────────────────────────────┘");
		} else {
			System.out.println("ERROR!!(회원 가입 실패)");
		}
	}
	
	// 회원 조회
	public void getMember() {
		List<Member> list = MemberManage.getInstance().getMember();
		System.out.println("────────── 회원 조회 ──────────");
		for(Member member : list) {
			System.out.println("  회원 ID : "+member.getMemberId());
			System.out.println("  회원 이름 : "+member.getMemberName());
			System.out.println("  회원 연락처 : "+member.getMemberTel());
			System.out.println(" ────────────────────────────");
		}
	}
	
	// 내 정보 조회
	public void getMyInfo() {
		List<Member> list = MemberManage.getInstance().getMemberInfo(MemberService.memberInfo.getMemberId());
		for(Member member : list) {
			System.out.println("┌───────── 나의 정보 ─────────┐");
			System.out.println("  이름 : "+member.getMemberName());
			System.out.println("  연락처 : "+member.getMemberTel());
			System.out.println("└───────────────────────────┘");
		}
	}
	
	// 회원 정보 수정 - 회원용
	public void updateMemberU() {
		System.out.println("┌─────────────────────────────────┐");
		System.out.println("  1. 비밀번호 변경 | 2. 연락처 변경 ");
		System.out.println("└─────────────────────────────────┘");
		System.out.print(">> ");
		int selectNo = Integer.parseInt(scn.nextLine());
		if(selectNo==1) {
			System.out.println("비밀번호를 다시 입력해주세요.");
			System.out.print(" ▶ ");
			String pw = scn.nextLine();
			if(MemberService.memberInfo.getMemberPw().equals(pw)) {
				System.out.println("수정할 비밀번호를 입력하세요.");
				System.out.print(" ▶ ");
				String pw1 = scn.nextLine();
				System.out.println("수정할 비밀번호를 한 번 더 입력하세요.");
				System.out.print(" ▶ ");
				String pw2 = scn.nextLine();
				if(pw1.equals(pw2)) {
					int result = MemberManage.getInstance().updateMemberP(MemberService.memberInfo.getMemberId(), pw1);
					MemberService.memberInfo.setMemberPw(pw1);
					if(result==1) {
						System.out.println("정상적으로 수정되었습니다.");
					} else {
						System.out.println("ERROR!!(회원 정보 수정 실패)");
					}
				} else {
					System.out.println("수정할 비밀번호를 잘못 입력하셨습니다.");
				}
			} else {
				System.out.println("비밀번호를 잘못 입력했습니다.");
			}
		} else if(selectNo==2) {
			System.out.println("비밀번호를 다시 입력해주세요.");
			System.out.print(" ▶ ");
			String pw = scn.nextLine();
			if(MemberService.memberInfo.getMemberPw().equals(pw)) {
				System.out.println("수정할 연락처를 입력하세요.");
				System.out.print(" ▶ ");
				String tel = scn.nextLine();
				int result = MemberManage.getInstance().updateMemberT(MemberService.memberInfo.getMemberId(), tel);
				if(result==1) {
					System.out.println("정상적으로 수정되었습니다.");
				} else {
					System.out.println("ERROR!!(회원 정보 수정 실패)");
				}
			} else {
				System.out.println("비밀번호를 잘못 입력했습니다.");
			}
		}
	}
	
	// 회원 탈퇴
	public void quitMember() {
		System.out.println("탈퇴할 회원의 ID를 입력하세요.");
		System.out.print(" ▶ ");
		String id = scn.nextLine();
		int result = MemberManage.getInstance().quitMember(id);
		if(result==1) {
			System.out.println("정상적으로 탈퇴되었습니다.");
		} else {
			System.out.println("ERROR!!(회원 탈퇴 실패)");
		}
	}
	
	// 회원 탈퇴 - 회원용
	public void quitMemberU() {
		System.out.println("정말 탈퇴하시겠습니까?");
		System.out.println("탈퇴하시려면 1을 입력해주세요.");
		System.out.print(" ▶ ");
		int select = Integer.parseInt(scn.nextLine());
		if(select==1) {
			int result = MemberManage.getInstance().quitMember(MemberService.memberInfo.getMemberId());
			if(result==1) {
				System.out.println("정상적으로 탈퇴되었습니다.");
			} else {
				System.out.println("ERROR!!(회원 탈퇴 실패)");
			}
		}
	}
}
