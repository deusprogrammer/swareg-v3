package com.swag.registration.email

import org.springframework.mail.javamail.JavaMailSenderImpl

class RequestCredentialsMailSender extends JavaMailSenderImpl {
	static String usernameOverride = null
	static String passwordOverride = null
	
	public static void overrideUsername(String username) {
		usernameOverride = username
	}
	
	public static void overridePassword(String password) {
		passwordOverride = password
	}
	
	public static void overrideCreds( String username, String password) {
		usernameOverride = username
		passwordOverride = password
	}
	
	public String getUsername() {
		println "GETTING USERNAME"
		return usernameOverride ?: super.getUsername()
	}

	public String getPassword() {
		println "GETTING PASSWORD"
		return passwordOverride ?: super.getPassword()
	}
}