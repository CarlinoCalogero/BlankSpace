package it.univaq.disim.oop.blankspace.domain;

public class GestoreSistema {
	private String nickname;
	private String password;
	
	public GestoreSistema(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}
	
}
