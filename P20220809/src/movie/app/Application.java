package movie.app;

import java.util.Scanner;

import movie.member.MemberService;

public class Application {
	
	Scanner scn = new Scanner(System.in);
	MemberService ms = new MemberService();
	int selectNo = 0;
	boolean run = true;
	
	public Application() {
		movieApp();
	}
	
	private void movieApp() {
		while(run) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃    WELCOME TO MOVIE APP!!    ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("  1. 로그인 │ 2. 회원가입 │ 3. 종료  ");
			System.out.println("└──────────────────────────────────┘");
			System.out.print(" ▶ ");
			selectNo = Integer.parseInt(scn.nextLine());
			switch(selectNo) {
			case 1:
				ms.loginId();
				if(MemberService.memberInfo!=null) {
					new Application2();
				}
				break;
			case 2:
				ms.registMemberU();
				break;
			case 3:
				System.out.println("MOVIE APP을 종료합니다.");
				run = false;
				break;
			}
		}
	}
}
