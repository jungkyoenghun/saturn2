package com.saturn.web;

import javax.servlet.http.HttpSession;

import com.saturn.domain.User;

public class HttpSessionUtils {
	
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession session) {
		
		System.out.println("isLoginUser enter!!!!!!!!!!!!!!!!!!!");
		
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		
		System.out.println(sessionedUser);
		
		if (sessionedUser == null) {
			
			System.out.println("sessionedUser is null!!!!!!!!!!!!!!!!!!!");
			
			return false;
		}
		
		System.out.println("sessionedUser is not null!!!!!!!!!!!!!!!!!!!");
		return true;
	}
	
	public static User getUserFromSession(HttpSession session){
		if (!isLoginUser(session)){
			return null;
		}
		return (User)session.getAttribute(USER_SESSION_KEY);
		
//		User sessionedUser = (User)session.getAttribute(USER_SESSION_KEY);
//		return sessionedUser;
		
	}
	

}
