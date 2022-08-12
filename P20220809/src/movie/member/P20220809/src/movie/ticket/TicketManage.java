package movie.ticket;

import java.util.ArrayList;
import java.util.List;

import movie.common.DAO;
import movie.theater.Theater;

public class TicketManage extends DAO {
	
	private static TicketManage tim = new TicketManage();
	private TicketManage() {
		
	}
	public static TicketManage getInstance() {
		return tim;
	}
	
	// 예매 - 영화 선택, 좌석 선택
	public int selectMovie(Ticket ticket) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO ticket VALUES(?, count_no.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticket.getMemberID());
			pstmt.setString(2, ticket.getMovieTitle());
			pstmt.setString(3, ticket.getMovieTimetable());
			pstmt.setString(4, ticket.getSeat());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 예매 - 선택한 좌석 채우기
	public int selectSeat(Ticket ticket) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET onoff = 1 WHERE (movie_title, movie_timetable, seat) IN (SELECT movie_title, movie_timetable, seat FROM ticket)";
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 예매 - 인원 선택
	public int numOfPeople(Ticket ticket) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO payment VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticket.getMemberID());
			pstmt.setInt(2, ticket.getNormalNo());
			pstmt.setInt(3, ticket.getYoungNo());
			pstmt.setInt(4, ticket.getOldNo());
			pstmt.setInt(5, ticket.getSpecialNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 결제 payment
	public List<Ticket> purchase(String id) {
		List<Ticket> list = new ArrayList<>();
		Ticket ticket = null;
		try {
			conn();
			String sql = "SELECT normal_no, young_no, old_no, special_no FROM payment WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ticket = new Ticket();
				ticket.setNormalNo(rs.getInt("normal_no"));
				ticket.setYoungNo(rs.getInt("young_no"));
				ticket.setOldNo(rs.getInt("old_no"));
				ticket.setSpecialNo(rs.getInt("special_no"));
				list.add(ticket);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 예매 취소 - 좌석비우기
	public int cancelTicket1(String id) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET onoff = 0 WHERE (movie_title, movie_timetable, seat) IN (SELECT movie_title, movie_timetable, seat FROM ticket WHERE member_id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 예매 취소 - 티켓 삭제
	public int cancelTicket2(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM ticket WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 예매 취소 - 좌석비우기 전부(상영관 영화 변경시)
	public int cancelTicket3(int theaterNo) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET onoff = 0 WHERE theater_no = ?";
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
	
	// 예매 취소 - 티켓 삭제 전부(상영관 영화 변경시)
	public int cancelTicket4(int theaterNo) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM ticket WHERE movie_title IN (SELECT movie_title FROM theater WHERE theater_no = ?)";
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
	
	// 예매 취소 - 좌석비우기 전부(시간표 변경시)
	public int cancelTicket5(int theaterNo, String time) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE theater SET onoff = 0 WHERE movie_timetable = ? AND theater_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			pstmt.setInt(2, theaterNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 예매 취소 - 티켓 번호 따라
	public int cancelTicket6(int theaterNo, String time) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM ticket WHERE movie_timetable = ? AND movie_title IN (SELECT movie_title FROM theater WHERE theater_no =?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			pstmt.setInt(2, theaterNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 예매 조회
	public List<Ticket> ticketInfo(String id){
		Ticket ticket = null;
		List<Ticket> list = new ArrayList<>();
		try {
			conn();
			String sql = "SELECT i.ticket_no, i.movie_title, i.movie_timetable, h.theater_no, i.seat\r\n"
					+ "FROM ticket i join theater h\r\n"
					+ "ON i.movie_title = h.movie_title AND i.seat = h.seat\r\n"
					+ "WHERE i.member_id = ? AND h.onoff = 1\r\n"
					+ "ORDER BY ticket_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ticket = new Ticket();
				ticket.setTicketNo(rs.getInt("ticket_no"));
				ticket.setMovieTitle(rs.getString("movie_title"));
				ticket.setMovieTimetable(rs.getString("movie_timetable"));
				ticket.setTheaterNo(rs.getInt("theater_no"));
				ticket.setSeat(rs.getString("seat"));
				list.add(ticket);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 가격표 조회
	public List<Ticket> priceInfo(){
		Ticket ticket = null;
		List<Ticket> list = new ArrayList<>();
		try {
			conn();
			String sql = "SELECT member_state, price FROM price";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ticket = new Ticket();
				ticket.setMemberState(rs.getString("member_state"));
				ticket.setPrice(rs.getInt("price"));
				list.add(ticket);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 가격 가져오기
	public List<Ticket> getPriceInfo(String state){
		Ticket ticket = null;
		List<Ticket> list = new ArrayList<>();
		try {
			conn();
			String sql = "SELECT price FROM price WHERE member_state =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ticket = new Ticket();
				ticket.setPrice(rs.getInt("price"));
				list.add(ticket);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
}
