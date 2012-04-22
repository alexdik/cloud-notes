package notes.model;

public class Note {
	private String name;
	private String text;
	private User user;
	
	public Note(String name, String text, User user) {
		this.name = name;
		this.text = text;
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
