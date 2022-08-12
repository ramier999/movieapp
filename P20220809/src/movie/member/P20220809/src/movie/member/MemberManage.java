package movie.member;

import java.util.ArrayList;
import java.util.List;

import movie.common.DAO;

public class MemberManage extends DAO{
	private static MemberManage mem = new MemberManage();
	private MemberManage() {
		
	}
	public static MemberManage getInstance() {
		return mem;
	}
	
	// 로그인
	public Member loginId(String id) {
		Member member = null;
		try {
			conn();
			String sql = "SELECT * FROM cinemamember WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				member.setRole(rs.getString("role"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}
	
	// 고객 등록
	public int registMember(Member member) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO cinemamember VALUES(?, ?, ?, ?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberTel());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 고객 조회
	public List<Member> getMember(){
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_id, member_name, member_tel FROM cinemamember WHERE role = '0'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				list.add(member);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 고객 정보 조회 - 개인
	public List<Member> getMemberInfo(String id){
		List<Member> list = new ArrayList<>();
		Member member = null;
		try {
			conn();
			String sql = "SELECT member_name, member_tel FROM cinemamember WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new Member();
				member.setMemberName(rs.getString("member_name"));
				member.setMemberTel(rs.getString("member_tel"));
				list.add(member);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 고객 정보 수정(비밀번호)
	public int updateMemberP(String id, String pw) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE cinemamember SET member_pw = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 고객 정보 수정(연락처)
	public int updateMemberT(String id, String tel) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE cinemamember SET member_tel = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	// 고객 탈퇴
	public int quitMember(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM cinemamember WHERE member_id = ?";
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
}