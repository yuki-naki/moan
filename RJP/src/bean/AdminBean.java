package bean;

public class AdminBean extends UserBean {

	public AdminBean(){ }

	public AdminBean(String name, String password){
		super();
		this.name = name;
		this.password = password;
		this.isAdmin = true;
	}
}
