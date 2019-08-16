/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import de.htwg.retroweb.entities.User;

@Service
public class SessionServiceImpl implements SessionService {
	
	private static final String IS_ADMIN = "IsAdmin";
	private static final String USER_NAME = "UserName";
	private static final String USER_ID = "users_id";
	
	@Override
	public boolean isLoggedIn(HttpSession session) {
		String user = (String) session.getAttribute(USER_NAME);
		return (user != null && !"".equalsIgnoreCase(user));
	}
	
	@Override
	public boolean isAdmin(HttpSession session) {
		String user = (String) session.getAttribute(USER_NAME);
		return user != null && !"".equalsIgnoreCase(user) && (boolean) session.getAttribute(IS_ADMIN);
	}
	
	@Override
	public String getUserName(HttpSession session) {
		return (String) session.getAttribute(USER_NAME);
	}
	
	@Override
	public long getUserId(HttpSession session) {
		return (long) session.getAttribute(USER_ID);
	}
	
	@Override
	public void addSession(HttpSession session, User user) {
        session.setAttribute(USER_NAME, user.getName());
        session.setAttribute(IS_ADMIN, user.isAdmin());
        session.setAttribute(USER_ID, user.getId());
	}

	@Override
	public void removeSession(HttpSession session) {
		session.removeAttribute(USER_NAME);
        session.removeAttribute(IS_ADMIN);
        session.removeAttribute(USER_ID);
        session.invalidate(); //invalidate will not set the session to null, if links to attributes are still present
	}
}
