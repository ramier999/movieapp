package movie.ticket;

import java.util.List;
import java.util.Scanner;

import movie.member.MemberManage;
import movie.member.MemberService;
import movie.theater.Theater;
import movie.theater.TheaterManage;
import movie.theater.TheaterService;

public class TicketService {
	public static Ticket ticketinfo = null; 
	MemberService mes = new MemberService();
	TheaterService ths = new TheaterService();
	Scanner scn = new Scanner(System.in);
	
	// 예매 - 관리자
	public void buyTicket() {
		Ticket ticket = new Ticket();
		System.out.println("예매할 회원의 ID를 입력하세요.");
		System.out.print(" ▶ ");
		String id = scn.nextLine();
		ticket.setMemberID(id);
		System.out.println("해당하는 분류에 예매할 인원을 입력하세요.");
		System.out.print("일반	▶ ");
		int normal = Integer.parseInt(scn.nextLine());
		ticket.setNormalNo(normal);
		TicketManage.getInstance().getPriceInfo("일반");
		System.out.print("청소년	▶ ");
		int young = Integer.parseInt(scn.nextLine());
		ticket.setYoungNo(young);
		System.out.print("경로	▶ ");
		int old = Integer.parseInt(scn.nextLine());
		ticket.setOldNo(old);
		System.out.print("우대	▶ ");
		int special = Integer.parseInt(scn.nextLine());
		ticket.setSpecialNo(special);
		TicketManage.getInstance().numOfPeople(ticket);
		int sum = normal + young + old + special;
		int normalP = normal * 13000;
		int youngP = young * 10000;
		int oldP = old * 7000;
		int specialP = special * 5000;
		for(int i=0; i<sum; i++) {
			System.out.println("예매할 영화 제목을 입력하세요.");
			System.out.print(" ▶ ");
			String title = scn.nextLine();
			System.out.println("예매할 영화 시간을 입력하세요.");
			System.out.print(" ▶ ");
			String time = scn.nextLine();
			System.out.println("예매할 좌석을 입력하세요.");
			System.out.print(" ▶ ");
			String seat = scn.nextLine();
			List<Theater> list = TheaterManage.getInstance().checkSeat(title, time, seat);
			for(Theater theater : list) {
				if(theater.getOnoff().equals("0")) {
					ticket.setMemberID(id);
					ticket.setMovieTitle(title);
					ticket.setMovieTimetable(time);
					ticket.setSeat(seat);
					TicketManage.getInstance().selectMovie(ticket);
					TicketManage.getInstance().selectSeat(ticket);
					System.out.println("예매 되었습니다.");
				} else if(theater.getOnoff().equals("1")) {
					System.out.println("이미 예매된 좌석입니다.");
					System.out.println("다른 좌석을 선택해주세요.");
					i--;
				}
			}	
		}
		System.out.println("매수 : "+sum+"매");
		System.out.println(id+" 회원님께서 결제하실 금액은 "+(normalP+youngP+oldP+specialP)+"원 입니다.");
	}
	
	// 상영관 영화 변경에 따른 티켓 취소
	public void registTheater() {
		Theater theater = new Theater();
		
		System.out.println("상영할 상영관의 번호를 입력하세요.");
		System.out.print(" ▶ ");
		int theaterNo = Integer.parseInt(scn.nextLine());
		theater.setTheaterNo(theaterNo);
		int ticket2 = TicketManage.getInstance().cancelTicket4(theaterNo);
		System.out.println("상영할 영화 제목을 입력하세요.");
		System.out.print(" ▶ ");
		String title = scn.nextLine();
		theater.setMovieTitle(title);
		int result = TheaterManage.getInstance().registTheater(theater);
		int ticket1 = TicketManage.getInstance().cancelTicket3(theaterNo);
		int theater1 = TheaterManage.getInstance().deleteTimetable(theaterNo);
		if(result==30) {
			System.out.println("제 "+theater.getTheaterNo()+" 상영관에서 '"+theater.getMovieTitle()+"' 영화를 상영합니다.");
		} else {
			System.out.println("ERROR!!(상영관 등록 실패)");
		}
	}
	
	// 상영 시간표 변경에 따른 티켓 취소
	public void registTimetable() {
		Theater theater = new Theater();
		
		System.out.println("상영할 시간을 지정할 상영관의 번호를 입력하세요.");
		System.out.print(" ▶ ");
		int theaterNo = Integer.parseInt(scn.nextLine());
		theater.setTheaterNo(theaterNo);
		System.out.println("상영관의 상영 순번을 입력하세요.");
		System.out.print(" ▶ ");
		int timeNo = Integer.parseInt(scn.nextLine());
		theater.setTimeNo(timeNo);
		System.out.println("상영할 시간을 입력하세요.");
		System.out.print(" ▶ ");
		String timetable = scn.nextLine();
		theater.setMovieTimeTable(timetable);
		int ticket2 = TicketManage.getInstance().cancelTicket6(theaterNo, timetable);
		int result = TheaterManage.getInstance().registTimetable(theater);
		int ticket1 = TicketManage.getInstance().cancelTicket5(theaterNo, timetable);
		if(result==10) {
			System.out.println("제 "+theater.getTheaterNo()+" 상영관에서 "+theater.getMovieTimeTable()+"에 영화를 상영합니다.");
		} else {
			System.out.println("ERROR!!(시간표 등록 실패)");
		}
	}
	
	// 예매 - 회원
	public void buyTicketU() {
		Ticket ticket = new Ticket();
		String id = MemberService.memberInfo.getMemberId();
		ticket.setMemberID(id);
		System.out.println("해당하는 분류에 예매할 인원을 입력하세요.");
		System.out.print("일반	▶ ");
		int normal = Integer.parseInt(scn.nextLine());
		ticket.setNormalNo(normal);
		System.out.print("청소년	▶ ");
		int young = Integer.parseInt(scn.nextLine());
		ticket.setYoungNo(young);
		System.out.print("경로	▶ ");
		int old = Integer.parseInt(scn.nextLine());
		ticket.setYoungNo(old);
		System.out.print("우대	▶ ");
		int special = Integer.parseInt(scn.nextLine());
		ticket.setSpecialNo(special);
		TicketManage.getInstance().numOfPeople(ticket);
		int sum = normal + young + old + special;
		int normalP = ticket.getNormalNo() * 13000;
		int youngP = ticket.getYoungNo() * 10000;
		int oldP = ticket.getOldNo() * 7000;
		int specialP = ticket.getSpecialNo() * 5000;
		for(int i=0; i<sum; i++) {
			System.out.println("예매할 영화 제목을 입력하세요.");
			System.out.print(" ▶ ");
			String title = scn.nextLine();
			System.out.println("예매할 영화 시간을 입력하세요.");
			System.out.print(" ▶ ");
			String time = scn.nextLine();
			System.out.println("예매할 좌석을 입력하세요.");
			System.out.print(" ▶ ");
			String seat = scn.nextLine();
			List<Theater> list = TheaterManage.getInstance().checkSeat(title, time, seat);
			for(Theater theater : list) {
				if(theater.getOnoff().equals("0")) {
					ticket.setMemberID(id);
					ticket.setMovieTitle(title);
					ticket.setMovieTimetable(time);
					ticket.setSeat(seat);
					TicketManage.getInstance().selectMovie(ticket);
					TicketManage.getInstance().selectSeat(ticket);
					System.out.println("예매 되었습니다.");
				} else if(theater.getOnoff().equals("1")) {
					System.out.println("이미 예매된 좌석입니다.");
					System.out.println("다른 좌석을 선택해주세요.");
					i--;
				}
			}	
		}
		System.out.println("매수 : "+sum+"매");
		System.out.println("결제하실 금액은 "+(normalP+youngP+oldP+specialP)+"원 입니다.");
	}
	
	// 예매 취소 - 관리자
	public void cancelTicketM() {
		System.out.println("예매 취소할 회원 ID를 입력하세요.");
		System.out.print(" ▶ ");
		String id = scn.nextLine();
		int result1 = TicketManage.getInstance().cancelTicket1(id);
		int result2 = TicketManage.getInstance().cancelTicket2(id);
		if(result1==0) {
			System.out.println("ERROR!!(예매 취소 실패)");
		} else {
			System.out.println("정상적으로 예매 취소 처리되었습니다.");
		}
	}
	
	// 예매 취소 - 회원
	public void cancelTicketU() {
		System.out.println("정말 예매를 취소하시겠습니까?");
		System.out.println("예매를 취소하시려면 1을 입력해주세요.");
		System.out.print(" ▶ ");
		int select = Integer.parseInt(scn.nextLine());
		if(select==1) {
			int result1 = TicketManage.getInstance().cancelTicket1(MemberService.memberInfo.getMemberId());
			int result2 = TicketManage.getInstance().cancelTicket2(MemberService.memberInfo.getMemberId());
			if(result1==0) {
				System.out.println("ERROR!!(예매 취소 실패)");
			} else {
				System.out.println("정상적으로 예매 취소 처리되었습니다.");
			}
		} else {
			System.out.println("예매를 취소하지 않고 돌아갑니다.");
		}
	}
	
	// 예매 조회 - 관리자
	public void ticketInfoM() {
		System.out.println("예매 조회할 회원 ID를 입력하세요.");
		System.out.print(" ▶ ");
		String id = scn.nextLine();
		List<Ticket> list = TicketManage.getInstance().ticketInfo(id);
		for(Ticket ticket : list) {
			System.out.printf("┌───────── TICKET %3d ─────────┐\n", ticket.getTicketNo());
			System.out.println("  << "+ticket.getMovieTitle()+" >>");
			System.out.println("  시간 : "+ticket.getMovieTimetable());
			System.out.println("  제 "+ticket.getTheaterNo()+" 상영관 좌석 : "+ticket.getSeat());
			System.out.println("└──────────────────────────────┘");
		} 
	}
	
	// 예매 조회 - 회원
	public void ticketInfoU() {
		List<Ticket> list = TicketManage.getInstance().ticketInfo(MemberService.memberInfo.getMemberId());
		for(Ticket ticket : list) {
			System.out.printf("┌───────── TICKET %3d ─────────┐\n", ticket.getTicketNo());
			System.out.println("  << "+ticket.getMovieTitle()+" >>");
			System.out.println("  시간 : "+ticket.getMovieTimetable());
			System.out.println("  제 "+ticket.getTheaterNo()+" 상영관 좌석 : "+ticket.getSeat());
			System.out.println("└──────────────────────────────┘");
		}
	}
	
	// 요금 조회
	public void priceInfo() {
		List<Ticket> list = TicketManage.getInstance().priceInfo();
		System.out.println("┌──────────────┐");
		for(Ticket ticket : list) {			
			System.out.println(" "+ticket.getMemberState()+" : "+ticket.getPrice());
		}
		System.out.println("└──────────────┘");
	}
	
}