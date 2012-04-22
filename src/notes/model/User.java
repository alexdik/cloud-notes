package notes.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	private String name;
	private String key;
	
	public User(String name, String key) {
		this.name = name;
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public String getKey() {
		return key;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
