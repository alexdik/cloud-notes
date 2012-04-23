package notes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import notes.model.Note;
import notes.model.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DatastoreHelper {
	public static User createUser(String userName, String socId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			UUID uid = UUID.randomUUID();
			String secretKey = uid.toString();
			Key key = KeyFactory.createKey(User.class.getSimpleName(), secretKey);
			User user = new User(key, userName, socId);
			pm.makePersistent(user);
			return user;
		} finally {
			pm.close();
		}
	}
	
	public static User getUser(String secretKey) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, secretKey);
			return user;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static void createNote(String secretKey, String noteName, String text) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = getUser(secretKey);
			Note note = new Note(noteName, text);
			List<Note> notes = user.getNoteSets();
			if (notes == null) {
				notes = new ArrayList<Note>();
				user.setNoteSets(notes);
			}
			notes.add(note);
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}
	
	public static Note getNote(String secretKey, long noteId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = getUser(secretKey);
			Key childKey = user.getUserKey().getChild(Note.class.getSimpleName(), noteId);
			Note note = pm.getObjectById(Note.class, childKey);
			return note;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static void updateNote(String secretKey, long noteId, Note newNote) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = getUser(secretKey);
			Key childKey = user.getUserKey().getChild(Note.class.getSimpleName(), noteId);
			newNote.setNoteKey(childKey);

			List<Note> notes = user.getNoteSets();
			if (notes == null) {
				notes = new ArrayList<Note>();
				user.setNoteSets(notes);
			}
			
			notes.add(newNote);
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}
	
	public static void test() {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = null;
			Note note = new Note("test Note", "content 123");
			List<Note> lst = new ArrayList<Note>();
			lst.add(note);
			
//			user.setNoteSets(lst);
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}
	
	public static void test2() {
//		System.out.println(createUser("Tom", "id11516f554").getSecretKey());
//		createNote("6e7641af-967d-4380-960c-199f01604fb4", "test Note", "content 123");
		updateNote("6e7641af-967d-4380-960c-199f01604fb4", 27, new Note("upd", "cont"));
//		System.out.println(getNote("1e471999-4f44-4081-8b0e-714eca2b35fc", 251).getText());
	}
	
	public static void test3() {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("userName == userNameParam");
		    query.declareParameters("String userNameParam");

		    @SuppressWarnings("unchecked")
			List<User> results = (List<User>) query.execute("Taro");
		    System.out.println(results.size());
		    System.out.println(results.get(1).getUserName());
		    Key key = results.get(0).getUserKey();
			
//			Key key = KeyFactory.createKey(User.class.getSimpleName(), 1);
			Key childKey = key.getChild(Note.class.getSimpleName(), 2);
			Note note = pm.getObjectById(Note.class, childKey);
		    
		    System.out.println(note == null);
		    System.out.println(note.getNoteKey().getId());
		    
		} finally {
			pm.close();
		}
	}
}
