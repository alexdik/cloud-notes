package notes.controller;

import notes.model.Note;
import notes.model.User;
import notes.util.DatastoreHelper;

public class Home extends AbstractController {
	public void home() {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		setPayload(render("home.html", user.getUserName()));
	}
	
	public void list() {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		setPayload(render("noteList.html", user.getUserName(), user.getNoteSets()));
	}
	
	public void edit(int id) {
		String userKey = getUserKey();
		Object[] userAndNote = DatastoreHelper.getUserAndNote(userKey, id);
		setPayload(render("edit.html", ((User)userAndNote[0]).getUserName(), (Note)userAndNote[1]));
	}
	
	public void newNote() {
		String userKey = getUserKey();
		User user = DatastoreHelper.getUser(userKey);
		setPayload(render("newNote.html", user.getUserName()));
	}
}
