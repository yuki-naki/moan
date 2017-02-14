package bean;

public class ClientBean extends UserBean {

	private String userId;

	public ClientBean(){ }

	public ClientBean(String userId, String name, String password){
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.isAdmin = false;
	}

	public String getId() {
		return userId;
	}

	public void setId(String userId) {
		this.userId = userId;
	}
}
