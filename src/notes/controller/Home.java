package notes.controller;

import java.util.Date;

import notes.util.DatastoreHelper;

public class Home extends AbstractController {
	public void index() {
		DatastoreHelper.test2();
		
		setPayload(render("home.html", "Taro", new Date()));
	}
}
