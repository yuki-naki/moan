package bean;

public class UserBean {

	private String userId;
	private String name;
	private String password;
	private boolean admin;

	public UserBean(){ }

	public UserBean(String userId, String name, String password){
		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return userId;
	}

	public void setId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
