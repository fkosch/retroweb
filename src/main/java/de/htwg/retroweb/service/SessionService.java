package de.htwg.retroweb.service;

import javax.servlet.http.HttpSession;

import de.htwg.retroweb.entities.User;

public interface SessionService {
	
	public boolean isLoggedIn(HttpSession session);
	public boolean isAdmin(HttpSession session);
	public String getUserName(HttpSession session);
	public long getUserId(HttpSession session);
	public void addSession(HttpSession session, User user);
	public void removeSession(HttpSession session);

}
