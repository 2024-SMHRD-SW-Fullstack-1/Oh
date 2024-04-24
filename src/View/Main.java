package View;

import java.util.Scanner;

import Controller.UserDAO;
import Model.UserDTO;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("====== 포켓몬스터 ======");
		while (true) {
			System.out.println("[1] 회원가입 \t [2] 로그인 \t [3] 회원탈퇴 \t [4] 종료");
			
			int choice = sc.nextInt();
			
			if (choice == 1) {
				// 회원가입 화면
				System.out.println("====== 회원가입 ======");
				
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				System.out.print("비밀번호 확인 : ");
				String pw2 = sc.next();
				System.out.print("닉네임 : ");
				String nick = sc.next();
				
				// 데이터베이스에 데이터 추가
				UserDAO uDao = new UserDAO();
				// DAO에 보낼 데이터를 묶어주기
				UserDTO uDto = new UserDTO(id, pw, pw2, nick);
				// 회원가입 메서드 사용하기
				int row = uDao.join(uDto);
				
				if (row > 0) {
					System.out.println("회원가입에 성공하였습니다.");
				} else {
					System.out.println("회원가입에 실패하였습니다.");
				}
			} else if (choice == 2) {
				System.out.println("===== 로그인 =====");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				
				// 데이터베이스에 데이터 추가
				UserDAO uDao = new UserDAO();
				// DAO에 보낼 데이터를 묶어주기
				UserDTO uDto = new UserDTO(id, pw);
				String uNick = uDao.login(uDto);
				
				if (uNick != null) {
					System.out.println(uNick + "님 환영합니다~");
				} else {
					System.out.println("로그인이 실패했습니다...");
					System.out.println("아이디와 비밀번호를 다시 확인해보세요.");
				}
			} else if (choice == 3) {
				System.out.println("===== 회원탈퇴 =====");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				
				// 데이터베이스에 데이터 추가
				UserDAO uDao = new UserDAO();
				// DAO에 보낼 데이터를 묶어주기
				UserDTO uDto = new UserDTO(id, pw);
				// 회원탈퇴 메서드 사용하기
				int row = uDao.delete(uDto);
				
				if (row > 0) {
					System.out.println("탈퇴하였습니다.");
				} else {
					System.out.println("탈퇴에 실패했습니다.");
				}
			} else if (choice == 4)  {
				System.out.println("종료합니다.");
				break;
			} else {
				System.out.println("오입력하였습니다.");
				System.out.println("종료합니다.");
				break;
			}
		}
	}
}
