package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Model.UserDTO;

public class UserDAO {
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	// 아이디 형식을 검증하는 정규표현식
    private static final String REGEX = "^[a-zA-Z0-9]{1,20}$";
    
	// 데이터베이스 연결 메서드
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String user = "campus_24SW_FS_p1_4";
			String password = "smhrd4";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 사용할 자원을 반납하는 메서드
	private void getClose() {
		try {
			if (rs != null) rs.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원가입 메서드
	public int join(UserDTO uDto) {
		int row = 0;
		try {
            // 아이디 형식 검증 (영어와 숫자로 20글자 이하)
            if (!validateIdPw(uDto.getId())) {
                System.out.println("아이디는 영어와 숫자로만 20글자 이하로 입력해주세요.");
                return row; // 아이디 형식이 올바르지 않으면 종료
            }
            // 비번 형식 검증 (영어와 숫자로 20글자 이하)
            if (!validateIdPw(uDto.getPw())) {
                System.out.println("비밀번호는 영어와 숫자로만 20글자 이하로 입력해주세요.");
                return row; // 비밀번호 형식이 올바르지 않으면 종료
            }
            
	        // 아이디 중복 확인
	        if (isDuplicateID(uDto.getId())) {
	            System.out.println("중복된 아이디입니다.");
	            return row; // 중복된 아이디일 경우 0을 반환하고 종료
	        }
	        // 비밀번호와 비밀번호 확인이 동일한지 확인
	        if (!isSamePW(uDto.getPw(), uDto.getPw2())) {
	            System.out.println("비밀번호가 다릅니다. 다시 입력해주세요.");
	            return row; // 중복된 아이디일 경우 0을 반환하고 종료
	        }
	        
	        // 중복되지 않은 경우 회원가입 처리
			getConn();
			String sql = "INSERT INTO USER_INFO(ID, PW, NICK) VALUES(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uDto.getId());
			psmt.setString(2, uDto.getPw());
			psmt.setString(3, uDto.getNick());
			row =  psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}
	
	// 아이디와 비번 형식을 검증하는 메서드 (영어와 숫자로 20글자 이하)
    private boolean validateIdPw(String str) {
        return Pattern.matches(REGEX, str);
    }
    
	// 아이디 중복 확인 메서드
	public boolean isDuplicateID(String id) {
	    boolean result = false;
	    try {
	        getConn();
	        String sql = "SELECT COUNT(*) FROM USER_INFO WHERE ID = ?";
	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, id);
	        ResultSet rs = psmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            result = count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        getClose();
	    }
	    return result;
	}
	
	// 비밀번호와 비밀번호 확인이 동일한 지 확인 메서드
	public boolean isSamePW(String pw, String pw2) {	
		boolean result = false;
		if (pw.equals(pw2)) result = true;
		return result;
	}
	
	// 로그인 메서드
	public String login(UserDTO uDto) {
		String uNick = "";
		try {
			getConn();
			String sql = "SELECT * FROM USER_INFO WHERE ID = ? AND PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uDto.getId());
			psmt.setString(2, uDto.getPw());
			rs = psmt.executeQuery();
			if (rs.next()) {
				uNick = rs.getString("NICK");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return uNick;
	}
	
	// 회원 탈퇴 기능
	public int delete(UserDTO uDto) {
		int row = 0;
		try {
			getConn();
			String sql = "DELETE FROM USER_INFO WHERE ID = ? AND PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uDto.getId());
			psmt.setString(2, uDto.getPw());
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}
}
