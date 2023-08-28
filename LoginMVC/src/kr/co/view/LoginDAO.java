package kr.co.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	Statement stmt;
	ResultSet rs;
	LoginView view;
	public LoginDAO() {
		view = new LoginView();
	}

	public void checkDuplicationId() {
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+LoginView.id+"' ");
			while (rs.next()) {
				System.out.println("ID가 중복되었습니다. 다른ID로 등록해주세요");
				view.correctId();
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	public void registInfoDb() {
		stmt = DBUtil.statement();
		LoginView.cnt = 0;
		try {
			LoginView.cnt = stmt.executeUpdate("insert into info(id, pw, pn, ad) values('"+LoginView.id+"', '"+LoginView.pw+"', '"+LoginView.pn+"', '"+LoginView.ad+"')");
			if (LoginView.cnt == 0) {
				System.out.println("등록실패\n");
			} else {
				System.out.println("등록성공\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	public void listShow() {
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info");
			while (rs.next()) {
				System.out.println("아이디	: " + rs.getString("id"));
				System.out.println("비밀번호	: " + rs.getString("pw"));
				System.out.println("주소	: " + rs.getString("ad"));
				System.out.println("전화번호	: " + rs.getString("pn"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	public void findSameId() {
		LoginView.cnt = 0;
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+LoginView.id+"'");
			while (rs.next()) {
				System.out.println("아이디	: " + rs.getString("id"));
				System.out.println("비밀번호	: " + rs.getString("pw"));
				System.out.println("주소	: " + rs.getString("ad"));
				System.out.println("전화번호	: " + rs.getString("pn"));
				System.out.println();
				LoginView.cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
		if (LoginView.cnt == 0) {
			System.out.println("해당아이디로 검색된 정보가 없습니다.");
		} else {
			System.out.println("출력완료");
		}
	}
	public void deleteId() {
		LoginView.cnt = 0;
		stmt = DBUtil.statement();
		try {
			LoginView.cnt = stmt.executeUpdate("delete from info where id = '"+LoginView.session+"'");
			if (LoginView.cnt > 0) {
				System.out.println("회원아이디-" + LoginView.session + "탈퇴성공");
				LoginView.session = null;
			} else {
				System.out.println("탈퇴실패...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	public void editDb() {
		LoginView.cnt = 0;
		stmt = DBUtil.statement();
		try {
			LoginView.cnt = stmt.executeUpdate("update info set id = '"+LoginView.id+"',  pw = '"+LoginView.pw+"', pn = '"+LoginView.pn+"', ad = '"+LoginView.ad+"' where id = '"+LoginView.session+"'");
			if (LoginView.cnt > 0) {
				System.out.println("회원정보 수정 완료");
				LoginView.session = LoginView.id;
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	public void loginCheck() {
		LoginView.cnt = 0;
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+LoginView.id+"' and pw = '"+LoginView.pw+"'");
			while (rs.next()) {
				LoginView.cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
		if (LoginView.cnt > 0) {
			System.out.println("로그인 완료");
			LoginView.session = LoginView.id;
		} else {
			System.out.println("로그인 실패");
		}
	}
}
