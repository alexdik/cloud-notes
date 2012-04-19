package notes.controller;

import java.util.Date;

public class Home extends AbstractController {
	public void index() {
		setPayload(render("home.html", "Taro", new Date()));
	}
}
