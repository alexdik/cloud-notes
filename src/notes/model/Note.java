package notes.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import notes.util.Const;

import com.google.appengine.api.datastore.Text;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Note {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key noteKey;
	private String noteName;
	private Text text;
	
	@Persistent
	private User user;
	
	public Note(String text) {
		int noteLen = text.length() > Const.MAX_NOTE_NAME_LEN ? Const.MAX_NOTE_NAME_LEN : text.length();
		String name = text.substring(0, noteLen).split("\r")[0];
		this.noteName = name;
		this.text = new Text(text);
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getText() {
		return text.getValue();
	}
	public void setText(String text) {
		this.text = new Text(text);
	}
	public User getUser() {
		return user;
	}
	public Key getNoteKey() {
		return noteKey;
	}
	public void setNoteKey(Key noteKey) {
		this.noteKey = noteKey;
	}
}
