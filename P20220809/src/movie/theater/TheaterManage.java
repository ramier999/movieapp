package movie.theater;

import java.util.ArrayList;
import java.util.List;

import movie.common.DAO;

public class TheaterManage extends DAO {
	private static TheaterManage thm = new TheaterManage();
	private TheaterManage() {
		
	}
	public static TheaterManage getInstance() {
		return thm;
	}
	
	// 상영관 - 영화 등록
	public int registTheater(Theater theater) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET movie_title = ? WHERE theater_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theater.getMovieTitle());
			pstmt.setInt(2, theater.getTheaterNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 상영관 - 시간표 등록
	public int registTimetable(Theater theater) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET movie_timetable = ? WHERE theater_no = ? AND time_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theater.getMovieTimeTable());
			pstmt.setInt(2, theater.getTheaterNo());
			pstmt.setInt(3, theater.getTimeNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 상영관 - 시간표 삭제
	public int deleteTimetable(int theaterNo) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET movie_timetable = null WHERE theater_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 영화 상영관 조회
	public List<Theater> theaterNoInfo() {
		List<Theater> list = new ArrayList<>();
		Theater theater = null;
		try {
			conn();
			String sql = "SELECT theater_no, movie_title FROM theater WHERE movie_title IS NOT NULL AND time_no = 1 AND seat = 'A1' ORDER BY theater_no";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				theater = new Theater();
				theater.setTheaterNo(rs.getInt("theater_no"));
				theater.setMovieTitle(rs.getString("movie_title"));
				list.add(theater);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 영화 시간표 조회
	public List<Theater> timetableInfo() {
		List<Theater> list = new ArrayList<>();
		Theater theater = null;
		try {
			conn();
			String sql = "SELECT movie_title, movie_timetable FROM theater WHERE movie_timetable IS NOT NULL AND seat = 'A1' ORDER BY movie_timetable";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				theater = new Theater();
				theater.setMovieTitle(rs.getString("movie_title"));
				theater.setMovieTimeTable(rs.getString("movie_timetable"));
				list.add(theater);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 영화 좌석 조회
	public List<Theater> seatInfo(String title, String time){
		List<Theater> list = new ArrayList<>();
		Theater theater = null;
		try { 
			conn();
			String sql = "SELECT onoff FROM theater WHERE movie_title = ? AND movie_timetable = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, time);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				theater = new Theater();
				theater.setOnoff(rs.getString("onoff"));
				list.add(theater);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 좌석 예매 여부
	public List<Theater> checkSeat(String title, String time, String seat) {
		List<Theater> list = new ArrayList<>();
		Theater theater = null;
		try {
			conn();
			String sql = "SELECT onoff FROM theater Where movie_title = ? AND movie_timetable = ? AND seat = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, time);
			pstmt.setString(3, seat);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				theater = new Theater();
				theater.setOnoff(rs.getString("onoff"));
				list.add(theater);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
