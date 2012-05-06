package notes.controller;

import java.io.IOException;

import notes.model.Note;
import notes.model.User;
import notes.util.DatastoreHelper;

public class Home extends AbstractController {
	public void home() throws IOException {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		
		if (userKey == null || user == null) {
			setPayload(render("home.html", new User(null, "No user found", null)));
		} else {
			getResponse().sendRedirect("/p/list");
		}
	}
	
	public void list() {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		setPayload(render("noteList.html", user, user.getNoteSets()));
	}
	
	public void edit(int id) {
		String userKey = getUserKey();
		Object[] userAndNote = DatastoreHelper.getUserAndNote(userKey, id);
		setPayload(render("edit.html", ((User)userAndNote[0]), (Note)userAndNote[1]));
	}
	
	public void newNote() {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		setPayload(render("newNote.html", user));
	}
}
