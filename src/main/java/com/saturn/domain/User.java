package com.saturn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue //가장 최근의 데이터 자동으로 1씩 증가
	private Long id;
	
	@Column(nullable=false, length=20, unique=true ) //F3 누르면 속성 볼수 있다
	private String userId;
	
	private String password;
	private String username;
	private String email;

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email
				+ "]";
	}

	public void update(User newUser) {
		this.password =newUser.password;
		this.username=newUser.username;
		this.email=newUser.email;
	}


}
