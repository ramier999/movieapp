package movie.movie;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MovieService {
	Scanner scn = new Scanner(System.in);
	
	// 영화 등록
	public void registMovie() {
		Movie movie = new Movie();
		
		System.out.println("영화 정보를 등록합니다.");
		System.out.print("제목 ▶ ");
		String title = scn.nextLine();
		movie.setMovieTitle(title);
		System.out.print("장르 ▶ ");
		String genre = scn.nextLine();
		movie.setMovieGenre(genre);
		System.out.print("등급 ▶ ");
		String age = scn.nextLine();
		movie.setMovieAge(age);
		System.out.print("러닝타임 ▶ ");
		int runtime = Integer.parseInt(scn.nextLine());
		movie.setMovieRuntime(runtime);
		System.out.print("상영시작 ▶ ");
		String start = scn.nextLine();
		movie.setMovieStart(start);
		System.out.print("상영종료 ▶ ");
		String end = scn.nextLine();
		movie.setMovieEnd(end);
		System.out.print("상세정보 ▶ ");
		String info = scn.nextLine();
		movie.setMovieInfo(info);
		
		int result = MovieManage.getInstance().registMovie(movie);
		if(result==1) {
			System.out.println("영화가 등록되었습니다.");
		} else {
			System.out.println("ERROR!!(영화 등록 실패)");
		}
	}
	
	// 영화 정보 (요약) - 전체
	public void movieInfo() {
		List<Movie> list = MovieManage.getInstance().movieInfo();

		System.out.println("┌── 현재 상영 중인 영화 ──┐");
		System.out.println(" ─────────────────────────");
		for(Movie movie : list) {
			System.out.println("  제목 : "+movie.getMovieTitle());
			System.out.println("  장르 : "+movie.getMovieGenre());
			System.out.println("  등급 : "+movie.getMovieAge());
			System.out.println(" ─────────────────────────");
		}
		System.out.println("└─────────────────────────┘");
	}
	
	// 영화 정보 (상세) - 검색
	public void movieDetailInfo() {
		System.out.println("영화 제목을 입력하세요.");
		System.out.print(" ▶ ");
		String title = scn.nextLine();
		List<Movie> list = MovieManage.getInstance().movieDetailInfo(title);
		System.out.println("┌───────────────── 영화 상세 정보 ─────────────────┐");
		for(Movie movie : list) {
			System.out.println("  제목 : "+movie.getMovieTitle());
			System.out.println("  장르 : "+movie.getMovieGenre());
			System.out.println("  등급 : "+movie.getMovieAge());
			System.out.println("  러닝타임 : "+movie.getMovieRuntime());
			System.out.println("  상세정보 : "+movie.getMovieInfo());
			System.out.println("  상영기간 : "+movie.getMovieStart().substring(0, 10)+" ~ "+movie.getMovieEnd().substring(0, 10));
			System.out.println("└─────────────────────────────────────────────────┘");
		}
	}
	
}
