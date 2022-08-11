package movie.theater;

import java.util.List;
import java.util.Scanner;

import movie.ticket.TicketManage;
import movie.ticket.TicketService;

public class TheaterService {
	Scanner scn = new Scanner(System.in);
	
	// 상영관 - 영화 등록
	public void registTheater() {
		Theater theater = new Theater();
		
		System.out.println("상영할 상영관의 번호를 입력하세요.");
		System.out.print(">> ");
		int theaterNo = Integer.parseInt(scn.nextLine());
		theater.setTheaterNo(theaterNo);
		System.out.println("상영할 영화 제목을 입력하세요.");
		System.out.print(">> ");
		String title = scn.nextLine();
		theater.setMovieTitle(title);
		
		int result = TheaterManage.getInstance().registTheater(theater);
		if(result==30) {
			System.out.println("제 "+theater.getTheaterNo()+" 상영관에서 '"+theater.getMovieTitle()+"' 영화를 상영합니다.");
		} else {
			System.out.println("ERROR!!(상영관 등록 실패)");
		}
	}
	
	// 상영관 - 시간표 등록
	public void registTimetable() {
		Theater theater = new Theater();
		
		System.out.println("상영할 시간을 지정할 상영관의 번호를 입력하세요.");
		System.out.print(">> ");
		int theaterNo = Integer.parseInt(scn.nextLine());
		theater.setTheaterNo(theaterNo);
		System.out.println("상영관의 상영 순번을 입력하세요.");
		System.out.print(">> ");
		int timeNo = Integer.parseInt(scn.nextLine());
		theater.setTimeNo(timeNo);
		System.out.println("상영할 시간을 입력하세요.");
		System.out.print(">> ");
		String timetable = scn.nextLine();
		theater.setMovieTimeTable(timetable);
		
		int result = TheaterManage.getInstance().registTimetable(theater);
		if(result==10) {
			System.out.println("제 "+theater.getTheaterNo()+" 상영관에서 "+theater.getMovieTimeTable()+"에 영화를 상영합니다.");
		} else {
			System.out.println("ERROR!!(시간표 등록 실패)");
		}
	}
	
	// 영화 시간표 조회
	public void timetableInfo() {
		List<Theater> list = TheaterManage.getInstance().timetableInfo();
		System.out.println("========== 영화 시간표 ==========");
		System.out.println("-------------------------------");
		for(Theater theater : list) {
			System.out.println("제목 : "+theater.getMovieTitle());
			System.out.println("시간 : "+theater.getMovieTimeTable());
			System.out.println("-------------------------------");
		}
		System.out.println("===============================");
	}
	
	// 영화 좌석 조회
	public void seatInfo() {
		System.out.println("영화 제목을 입력하세요.");
		System.out.print(">> ");
		String title = scn.nextLine();
		System.out.println("영화 시간을 입력하세요.");
		System.out.print(">> ");
		String time = scn.nextLine();
		List<Theater> list = TheaterManage.getInstance().seatInfo(title, time);
		System.out.println("[빈 좌석은 □, 예매된 좌석은 ■로 표시됩니다.]");
		System.out.println(" A1 A2 A3 A4 A5 B1 B2 B3 B4 B5");
		for(Theater theater : list) {
			if(theater.getOnoff().equals("0")) {
				System.out.print(" □ ");
			} else if(theater.getOnoff().equals("1")) {
				System.out.print(" ■ ");
			}
		}
		System.out.println();
	}
	
	// 좌석 예매 여부
	public void checkSeat() {
		System.out.println("영화 제목을 입력하세요.");
		System.out.print(">> ");
		String title = scn.nextLine();
		System.out.println("영화 시간을 입력하세요.");
		System.out.print(">> ");
		String time = scn.nextLine();
		System.out.println("좌석을 입력하세요.");
		System.out.print(">> ");
		String seat = scn.nextLine();
		List<Theater> list = TheaterManage.getInstance().checkSeat(title, time, seat);
		for(Theater theater : list) {
			if(theater.getOnoff().equals("0")) {
				System.out.println("예매 가능합니다.");
			} else if(theater.getOnoff().equals("1")) {
				System.out.println("이미 예매된 좌석입니다.");
			}
		}
	}

}
