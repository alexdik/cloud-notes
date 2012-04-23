package notes.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key userKey;
	private String userName;
	private String socId;
	
	@Persistent(mappedBy = "user")
    private List<Note> noteSets;
	
	public User(Key userKey, String userName, String socId) {
		this.userKey = userKey;
		this.userName = userName;
		this.socId = socId;
	}
	public String getUserName() {
		return userName;
	}
	public String getSecretKey() {
		return userKey.getName();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Note> getNoteSets() {
		return noteSets;
	}
	public void setNoteSets(List<Note> noteSets) {
		this.noteSets = noteSets;
	}
	public Key getUserKey() {
		return userKey;
	}
	public void setUserKey(Key userKey) {
		this.userKey = userKey;
	}
	public String getSocId() {
		return socId;
	}
	public void setSocId(String socId) {
		this.socId = socId;
	}
}
