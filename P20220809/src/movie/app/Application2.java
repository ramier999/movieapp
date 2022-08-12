package movie.app;

import java.util.Scanner;

import movie.member.MemberService;
import movie.movie.MovieService;
import movie.theater.TheaterService;
import movie.ticket.TicketService;

public class Application2 {
	
	Scanner scn = new Scanner(System.in);
	MemberService mes = new MemberService();
	MovieService mos = new MovieService();
	TheaterService ths = new TheaterService();
	TicketService tis = new TicketService();
	int selectNo = 0;
	
	public Application2() {
		movieApp2();
	}
	
	private void movieApp2() {
		while(true) {
			if(MemberService.memberInfo.getRole().equals("1")) {
				System.out.println("┌─────────────────────────────────────────────────────────┐");
				System.out.println("  1. 회원 관리 | 2. 영화 관리 | 3. 예매 관리 | 4. 로그아웃 ");
				System.out.println("└─────────────────────────────────────────────────────────┘");
				System.out.print(" ▶ ");
				selectNo = Integer.parseInt(scn.nextLine());
				if(selectNo==1) {
					System.out.println("┌──────────────────────────────────────────────────┐");
					System.out.println("  1. 신규 회원 가입 | 2. 회원 조회 | 3. 회원 탈퇴 ");
					System.out.println("└──────────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						mes.registMember();
					} else if(selectNo==2) {
						mes.getMember();
					} else if(selectNo==3) {
						mes.quitMember();
					}
				} else if(selectNo==2) { 
					System.out.println("┌──────────────────────────────────────────────────────────────┐");
					System.out.println("  1. 영화 정보 등록 | 2. 영화 정보 조회 | 3. 영화 상영관 등록  ");
					System.out.println("└──────────────────────────────────────────────────────────────┘");
					System.out.println("┌─────────────────────────────────────────────────────────────────┐");
					System.out.println("  4. 영화 상영관 조회 | 5. 영화 시간표 등록 | 6. 영화 시간표 조회 ");
					System.out.println("└─────────────────────────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						mos.registMovie();
					} else if(selectNo==2) {
						mos.movieInfo();
						mos.movieDetailInfo();
					} else if(selectNo==3) {
						tis.registTheater();
					} else if(selectNo==4) {
						ths.theaterNoInfo();
					} else if(selectNo==5) {
						tis.registTimetable();
					} else if(selectNo==6) {
						ths.timetableInfo();
					}
				} else if(selectNo==3) {
					System.out.println("┌────────────────────────────────────────────────────────────┐");
					System.out.println("  1. 회원 영화 예매 | 2. 회원 예매 취소 | 3. 회원 예매 조회  ");
					System.out.println("└────────────────────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						ths.timetableInfo();
						tis.buyTicket();
					} else if(selectNo==2) {
						tis.cancelTicketM();
					} else if(selectNo==3) {
						tis.ticketInfoM();
					}
				} else if(selectNo==4) {
					mes.logoutId();
					break;
				}
			} else if(MemberService.memberInfo.getRole().equals("0")) {
				System.out.println("┌────────────────────────────────────────────────────────────────┐");
				System.out.println("  1. 상영 영화 정보 | 2. 영화 예매 | 3. 마이페이지 | 4. 로그아웃 ");
				System.out.println("└────────────────────────────────────────────────────────────────┘");
				System.out.print(" ▶ ");
				selectNo = Integer.parseInt(scn.nextLine());
				if(selectNo==1) {
					System.out.println("┌────────────────────────────────────────────┐");
					System.out.println("  1. 현재 상영 중인 영화 | 2. 영화 상세 정보 ");
					System.out.println("└────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						mos.movieInfo();
					} else if(selectNo==2) {
						mos.movieDetailInfo();
					}
				} else if(selectNo==2) {
					System.out.println("┌───────────────────────────────────────────────────────────┐");
					System.out.println("  1. 좌석 조회 | 2. 영화 예매 | 3. 예매 취소 | 4. 요금 조회  ");
					System.out.println("└───────────────────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						ths.timetableInfo();
						ths.seatInfo();
					} else if(selectNo==2) {
						ths.timetableInfo();
						tis.buyTicketU();
					} else if(selectNo==3) {
						tis.cancelTicketU();
					} else if(selectNo==4) {
						tis.priceInfo();
					}
				} else if(selectNo==3) {
					System.out.println("┌───────────────────────────────────────────────────────┐");
					System.out.println("  1. 예매 확인 | 2. 내 정보 | 3. 내 정보 수정 | 4. 탈퇴 ");
					System.out.println("└───────────────────────────────────────────────────────┘");
					System.out.print(" ▶ ");
					selectNo = Integer.parseInt(scn.nextLine());
					if(selectNo==1) {
						tis.ticketInfoU();
					} else if(selectNo==2) {
						mes.getMyInfo();
					} else if(selectNo==3) {
						mes.updateMemberU();
					} else if(selectNo==4) {
						mes.quitMemberU();
						break;
					}
				} else if(selectNo==4) {
					mes.logoutId();
					break;
				}
			}
		}
	}
}