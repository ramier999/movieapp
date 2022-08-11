package movie.movie;

import java.util.ArrayList;
import java.util.List;

import movie.common.DAO;

public class MovieManage extends DAO{
	private static MovieManage mom = new MovieManage();
	private MovieManage() {
		
	}
	public static MovieManage getInstance() {
		return mom;
	}
	
	// 영화 등록
	public int registMovie(Movie movie){
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO movie VALUES(?, ?, ?, ?, ?, ? ,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovieTitle());
			pstmt.setString(2, movie.getMovieGenre());
			pstmt.setInt(3, movie.getMovieRuntime());
			pstmt.setString(4, movie.getMovieInfo());
			pstmt.setString(5, movie.getMovieStart());
			pstmt.setString(6, movie.getMovieEnd());
			pstmt.setString(7, movie.getMovieAge());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 영화 정보 (요약) - 전체
	public List<Movie> movieInfo() {
		List<Movie> list = new ArrayList<>();
		Movie movie = null;
		try{
			conn();
			String sql = "SELECT movie_title, movie_genre, movie_age FROM movie";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				movie = new Movie();
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieAge(rs.getString("movie_age"));
				list.add(movie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 영화 정보 (상세) - 검색
	public List<Movie> movieDetailInfo(String title) {
		Movie movie = null;
		List<Movie> list = new ArrayList<>();
		try{
			conn();
			String sql = "SELECT * FROM movie WHERE movie_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				movie = new Movie();
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieRuntime(rs.getInt("movie_runtime"));
				movie.setMovieInfo(rs.getString("movie_info"));
				movie.setMovieStart(rs.getString("movie_start"));
				movie.setMovieEnd(rs.getString("movie_end"));
				movie.setMovieAge(rs.getString("movie_age"));
				list.add(movie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 날짜 정보 변경
	public List<Movie> movieDateInfo(String title) {
		Movie movie = null;
		List<Movie> list = new ArrayList<>();
		try{
			conn();
			String sql = "SELECT TO_CHAR(movie_start, 'YYMMDD') as movie_start, TO_CHAR(movie_end, 'YYMMDD') as movie_end FROM movie WHERE movie_title =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				movie = new Movie();
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieStart(rs.getString("movie_start"));
				movie.setMovieEnd(rs.getString("movie_end"));
				list.add(movie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
