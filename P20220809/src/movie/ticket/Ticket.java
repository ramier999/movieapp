package movie.ticket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ticket {
	private int ticketNo;
	private String memberID;
	private String movieTitle;
	private String movieTimetable;
	private int theaterNo;
	private String seat;
	private int normalNo;	// 일반
	private int youngNo;	// 청소년
	private int oldNo;		// 경로
	private int specialNo;	// 우대
	private String memberState; 
	private int price;
	
}
