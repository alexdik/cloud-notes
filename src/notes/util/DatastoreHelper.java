package notes.util;

import javax.jdo.PersistenceManager;

import notes.model.User;


public class DatastoreHelper {
	public static void test() {
		PersistenceManager pm = DatastoreManager.get().getPersistenceManager();
		try {
			User user = new User("Taro", "123");
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}
}
