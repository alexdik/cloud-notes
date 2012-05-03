package notes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import notes.model.Note;
import notes.model.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DatastoreHelper {
	public static User createUser(String userName, String socId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			UUID uid = UUID.randomUUID();
			String userKey = uid.toString();
			Key key = KeyFactory.createKey(User.class.getSimpleName(), userKey);
			User user = new User(key, userName, socId);
			pm.makePersistent(user);
			return user;
		} finally {
			pm.close();
		}
	}
	
	public static User getUser(String userKey) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
			return user;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static void createNote(String userKey, Note note) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
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
	
	public static Note getNote(String userKey, long noteId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
			Key childKey = user.getUserKey().getChild(Note.class.getSimpleName(), noteId);
			Note note = pm.getObjectById(Note.class, childKey);
			return note;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static Object[] getUserAndNote(String userKey, long noteId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
			Key childKey = user.getUserKey().getChild(Note.class.getSimpleName(), noteId);
			Note note = pm.getObjectById(Note.class, childKey);
			return new Object[] {user, note};
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
	
	public static void updateNote(String userKey, long noteId, Note newNote) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
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
	
	public static void deleteNote(String userKey, long noteId) {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = pm.getObjectById(User.class, userKey);
			Key childKey = user.getUserKey().getChild(Note.class.getSimpleName(), noteId);
			Note note = pm.getObjectById(Note.class, childKey);
			pm.deletePersistent(note);
		} finally {
			pm.close();
		}
	}
}
