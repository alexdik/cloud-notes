package notes.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import notes.model.User;
import notes.util.Const;
import notes.util.DatastoreHelper;
import notes.util.UrlFetcher;
import restdisp.worker.AbstractWorker;

import net.sf.json.JSONObject;

public class Auth extends AbstractWorker {
	private static final int URL_PREFIX_LEN = "access_token=".length();
	private static final int COOKIE_EXP = 12 * 30 * 86400;
	private static Properties props = System.getProperties(); 
	private static String appId = props.getProperty("fb_app_id");
	private static String callbackURL = props.getProperty("fb_callback_url");
	private static String clientSecret = props.getProperty("fb_secret");
	
	public void signOut() throws IOException {
		Cookie cookie = new Cookie(Const.AUTH, "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
		getResponse().sendRedirect("/p");
	}
	
	public void facebookLogin() {
		String code = getRequest().getParameter("code");
		
		if (code != null && !code.isEmpty()) {
			String getTokenURL = "https://graph.facebook.com/oauth/access_token"
					+ "?client_id=" + appId + "&redirect_uri=" + callbackURL
					+ "&client_secret=" + clientSecret + "&code=" + code;
			
			try {
				String tokenRsp = UrlFetcher.get(getTokenURL);
				
				String token = URLEncoder.encode(formatUrl(tokenRsp), Const.ENCODING);
				String url = "https://graph.facebook.com/me?access_token=" + token;
				String userData = UrlFetcher.get(url);
				
				JSONObject jsonObj = JSONObject.fromObject(userData);
				if (jsonObj.containsKey("name") && jsonObj.containsKey("id")) {
					String userName = jsonObj.getString("name");
					String userId = jsonObj.getString("id");
					String userSecret = null;
					
					User user = DatastoreHelper.getUserById(userId);
					if (null == user) {
						user = DatastoreHelper.createUser(userName, userId);
						userSecret = user.getSecretKey();
					} else {
						userSecret = user.getSecretKey();
					}
					
					Cookie cookie = new Cookie(Const.AUTH, userSecret);
					cookie.setPath("/");
					cookie.setMaxAge(COOKIE_EXP);
					getResponse().addCookie(cookie);
					
					getResponse().sendRedirect("/p/list");
				} else {
					redirectToLogin(getResponse());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			redirectToLogin(getResponse());
		}
	}
	
	private void redirectToLogin(HttpServletResponse resp) {
		String fbLoginPage = "https://graph.facebook.com/oauth/authorize"
				+ "?client_id=" + appId + "&redirect_uri=" + callbackURL;

		try {
			resp.sendRedirect(fbLoginPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String formatUrl(String urlStr) {
		String url = urlStr.substring(URL_PREFIX_LEN);
		int ampInd = url.indexOf("&");
		url = url.substring(0, ampInd);
		
		return url;
	}
}
