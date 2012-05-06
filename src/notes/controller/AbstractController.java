package notes.controller;

import javax.servlet.http.Cookie;

import notes.util.Const;

import cn.bran.japid.compiler.OpMode;
import cn.bran.japid.template.JapidRenderer;
import restdisp.worker.AbstractWorker;

public class AbstractController extends AbstractWorker {
	static {
		JapidRenderer.init(OpMode.prod, ".", 5);
	}

	public String render(String view, Object... model) {
		return JapidRenderer.renderWith(view, model);
	}
	
	public String getUserKey() {
		if(getRequest().getCookies() != null) {
			for (Cookie cookie : getRequest().getCookies()) {
				if (cookie.getName().equals(Const.AUTH)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
