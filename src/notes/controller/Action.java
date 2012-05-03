package notes.controller;

import java.io.IOException;

import notes.model.Note;
import notes.util.DatastoreHelper;

public class Action extends AbstractController {
	public void add() throws IOException {
		String userKey = getUserKey();
		Note note = new Note(getRequest().getParameter("text"));
		DatastoreHelper.createNote(userKey, note);
		getResponse().sendRedirect("/p/list");
	}
	
	public void update(int id) throws IOException {
		String userKey = getUserKey();
		Note note = new Note(getRequest().getParameter("text"));
		DatastoreHelper.updateNote(userKey, id, note);
		getResponse().sendRedirect("/p/list");
	}
	
	public void delete(int id) throws IOException {
		String userKey = getUserKey();
		DatastoreHelper.deleteNote(userKey, id);
		getResponse().sendRedirect("/p/list");
	}
}
