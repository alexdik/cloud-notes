package notes.controller;

import notes.model.User;
import notes.util.DatastoreHelper;

public class Home extends AbstractController {
	public void index() {
		User user = DatastoreHelper.getUser("6ff8d868-dec7-4295-98d9-7869d797dd0d");
		setPayload(render("noteList.html", "John", user.getNoteSets()));
	}
	
	public void init() {
		DatastoreHelper.test2();
	}
}
