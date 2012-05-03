package notes.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;

import restdisp.worker.AbstractWorker;

import notes.model.User;
import notes.util.Const;
import notes.util.DatastoreHelper;

public class Auth extends AbstractWorker {
	public void authenticate(String userName, String userId) throws IOException {
		User user = DatastoreHelper.createUser(userName, userId);
		
		Cookie cookie = new Cookie(Const.AUTH, user.getSecretKey());
		cookie.setMaxAge(86400);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
		getResponse().sendRedirect("/p");
	}
}
