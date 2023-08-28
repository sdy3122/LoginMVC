package kr.co.controller;

import kr.co.view.LoginDAO;
import kr.co.view.LoginView;

public class LoginController {
	public LoginView view;
	public LoginDAO dao;
	public LoginController() {
		view = new LoginView();
		dao = new LoginDAO();
	}

	public void startApp() {
		while (true) {
			view.menuTitle();
			view.inputStr(10);
			view.checkEnglish();
			if (view.choice == 'r' || view.choice == 'R') {
				if (LoginView.session == null) {
					view.registTitle();
					view.correctId();
					view.correctPw();
					view.correctAd();
					view.correctPn();
					view.registInfoAl();
					dao.registInfoDb();
				} else {
					view.logoutWarning();
				}
			} else if (view.choice == 'l' || view.choice == 'L') {
				if (LoginView.session == null) {
					view.loginWarning();
				} else {
					view.listTitle();
					dao.listShow();
				}
			} else if (view.choice == 's' || view.choice == 'S') {
				if (LoginView.session == null) {
					view.loginWarning();
				} else {
					view.searchIdTitle();
					view.inputStr(20);
					view.inputId();
					dao.findSameId();
				}
			} else if (view.choice == 'd' || view.choice == 'D') {
				if (LoginView.session == null) {
					view.loginWarning();
				} else {
					view.deleteIdTitle();
					dao.deleteId();					
				}
			} else if (view.choice == 'e' || view.choice == 'E') {
				if (LoginView.session == null) {
					view.loginWarning();
				} else {
					view.editTitle();
					view.correctId();
					view.correctPw();
					view.correctAd();
					view.correctPn();
					dao.editDb();			
				}
			} else if (view.choice == 'i' || view.choice == 'I') {
				if (LoginView.session == null) {
					view.loginTitle();
					view.inputIdTitle();
					view.inputStr(20);
					view.inputId();
					view.correctPw();
					dao.loginCheck();
				} else {
					view.logoutWarning();
				}
			} else if (view.choice == 'o' || view.choice == 'O') {
				if (LoginView.session == null) {
					view.loginWarning();
				} else {
					view.logoutTitle();
					view.logout();
				}
			} else if (view.choice == 'x' || view.choice == 'X') {
				view.sysExit();
				break;
			} else {
				view.correctAlphaBet();
			}
		}
	}
}