package in.pathri.gaana.dao;

public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6794124712995006043L;
	private String userName;
	private String password;
	private String token;
//	private boolean doAlbumSplit = true;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isEmpty() {
		if (userName.isEmpty() || password.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean hasToken() {
		return (null != token && !token.isEmpty());
	}
}
