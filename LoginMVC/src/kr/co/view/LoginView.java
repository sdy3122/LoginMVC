package kr.co.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginView {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static String session;
	public String checkedString;
	public String inputString;
	public char choice;
	public static String id;
	public static String pw;
	public static String ad;
	public static String pn;
	public static int cnt;
	public String checkNum;
	static ArrayList<LoginDTO> al = new ArrayList<LoginDTO>();
	
	LoginDAO dao;
	public LoginView() {
		dao = new LoginDAO();
	}
	
	public void loginWarning() {
		System.out.println("로그인부터 하세요");
	}
	public void logoutWarning() {
		System.out.println("로그아웃부터 하세요");
	}
	public void menuTitle() { 
		System.out.println("R:회원가입 L:회원목록 S:ID찾기 D:회원탈퇴 E:회원수정 I:로그인 O:로그아웃 X:종료");
	}
	// 공백, 길이확인 메소드
	public void checkEmptyString(String str, int sdy) {
		if (str.trim().isEmpty()) {
			try {
				throw new MyExceptionEmpty();
			} catch (MyExceptionEmpty e) {
				e.getMessage();
				inputStr(sdy);
			}
		}
		if (str.length() > sdy) {
			System.out.println(sdy + "자내로 입력해주세요");
			inputStr(sdy);
		}
	}
	public void inputStr(int sdy) {
		try {
			inputString = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkEmptyString(inputString, sdy);
		checkedString = inputString;
	}
	public void checkEnglish() {
		choice = checkedString.charAt(0);	
		try {
			if (!(choice > 64 && choice < 123)) {
				throw new MyException("알파벳만 입력하시오");
			}
		} catch (MyException e) {
			System.out.println(e.getMessage());
			inputStr(10);
		}
	}
	
	public void registTitle() {
		System.out.println("회원정보등록");
	}
	public void inputIdTitle() {
		System.out.print("아이디 입력 : ");
	}
	public void inputId() {
		id = checkedString;
	}
	public void correctId() {
		inputIdTitle();
		inputStr(20);
		inputId();
		dao.checkDuplicationId();
	}
	public void inputPwTitle() {
		System.out.print("패스워드 입력 : ");
	}
	public void inputPw() {
		pw = checkedString;
	};
	public void correctPw() {
		inputPwTitle();
		inputStr(20);
		inputPw();
	}
	public void inputAdTitle() {
		System.out.print("주소입력 : ");
	}
	public void inputAd() {
		ad = checkedString;
	}
	public void correctAd() {
		inputAdTitle();
		inputStr(100);
		inputAd();
	}
	public void inputPnTitle() {
		System.out.print("전화번호 입력 : ");
	}
	public void checkPnNumber() {
		cnt = 0;
		checkNum = checkedString;
		for (int i = 0; i < checkNum.length(); i++) {
			if (!(checkNum.charAt(i) > 47 && (checkNum.charAt(i) < 58))) {
				cnt++;
				break;
			}
		}
		if (cnt > 0) {
			System.out.println("숫자만 입력하세요");	
			correctPn();
		} else {
			pn = checkNum;
		}
	}
	public void correctPn() {
		inputPnTitle();
		inputStr(11);
		checkPnNumber();
	}
	public void registInfoAl() {
		LoginDTO dto = new LoginDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setAd(ad);
		dto.setPn(pn);
		al.add(dto);
	}
	public void listTitle() {
		System.out.println("전체출력");
	}
	public void searchIdTitle() {
		System.out.println("회원정보검색");
		System.out.print("찾는 ID 입력 : ");
	}
	public void deleteIdTitle() {
		System.out.println("회원탈퇴");
	}
	public void editTitle() {
		System.out.println("수정메뉴");
	}
	public void loginTitle() {
		System.out.println("로그인");
	}
	public void logoutTitle() {
		System.out.println("로그아웃");
	}
	public void logout() {
		session = null;
	}
	public void sysExit() {
		System.out.println("종료!");
	}
	public void correctAlphaBet() {
		System.out.println("올바른 알파벳 입력");
	}
}
