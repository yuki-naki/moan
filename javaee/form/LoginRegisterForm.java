package form;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bean.ClientBean;
import bean.UserBean;
import sql.ConnectionDB;
import sql.Query;

public final class LoginRegisterForm {

    private static final String NAME_REGISTER = "nameRegister";
    private static final String PASS_REGISTER = "passRegister";
    private static final String CONFIRM = "confirm";
    private static final String NAME_LOGIN = "nameLogin";
    private static final String PASS_LOGIN = "passLogin";

    private boolean success = false;
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean getSuccess(){
    	return success;
    }

    private void checkName(String name) throws Exception {
        if(name == null || name.trim().equals("")){
            throw new Exception("名前を入力してください");
        }
    }

    private void checkPass(String pass) throws Exception {
        if(pass == null || pass.trim().equals("")){
            throw new Exception("パスワードを入力してください");
        }
    }

    private void checkConfirm(String pass, String confirm) throws Exception {

        if(confirm == null || confirm.trim().equals("")){
        	throw new Exception("パスワードを再入力してください");
        }
        else if(! pass.equals(confirm)){
                throw new Exception( "パスワードと同じく入力してください");
        }
    }

    private static String getInputValue(HttpServletRequest request, String paramName){

        String value = request.getParameter(paramName);
        if(value == null || value.trim().length() == 0){
            return null;
        }
        else{
            return value.trim();
        }
    }

    public ClientBean clientRegister(HttpServletRequest request){

    	String name = getInputValue(request, NAME_REGISTER);
    	String pass = getInputValue(request, PASS_REGISTER);
        String confirm = getInputValue(request, CONFIRM);
        ClientBean client = new ClientBean();

        try {
            checkName(name);
        }
        catch(Exception ex) {
            errors.put(NAME_REGISTER, ex.getMessage());
        }

        client.setName(name);

        try {
            checkPass(pass);
        }
        catch(Exception ex) {
        	errors.put(PASS_REGISTER, ex.getMessage());
        }

        client.setPassword(pass);

        try {
            checkConfirm(pass, confirm);
        }
        catch(Exception ex) {
        	errors.put(CONFIRM, ex.getMessage());
        }

        client.setIsAdmin(false);

        if(errors.isEmpty()){
        	if(ConnectionDB.openConnection()){
	        	try {
					if(Query.hasClient(ConnectionDB.getConnection(), name)){
						throw new Exception("ユーザーは既に登録されています");
					}
					else {
						Query.insertClient(ConnectionDB.getConnection(), client);
						success = true;
					}
				}
	        	catch(SQLException ex){
					ex.printStackTrace();
				}
	        	catch(Exception ex){
	        		errors.put(NAME_REGISTER, ex.getMessage());
				}
	        	finally {
	        		ConnectionDB.closeConnection();
	        	}
        	}
        }
        return client;
    }

    public UserBean userLogin(HttpServletRequest request){

    	String name = getInputValue(request, NAME_LOGIN);
        String pass = getInputValue(request, PASS_LOGIN);
        UserBean user = new UserBean();

        try {
            checkName(name);
        }
        catch(Exception ex) {
            errors.put(NAME_LOGIN, ex.getMessage());
        }

        user.setName(name);

        try {
            checkPass(pass);
        }
        catch(Exception ex) {
        	errors.put(PASS_LOGIN, ex.getMessage());
        }

        user.setPassword(pass);

        if(errors.isEmpty()){
        	if(ConnectionDB.openConnection()){
	        	try {
	        		boolean isAdmin = Query.isAdmin(ConnectionDB.getConnection(), name);
	        		if(isAdmin){
	        			user = Query.getAdmin(ConnectionDB.getConnection(), name, pass);
	        			if(user == null){
							throw new Exception("パスワードは間違っています");
						}
	        		}
	        		else {
	        			user = Query.getClient(ConnectionDB.getConnection(), name, pass);
	        			if(user == null){
							throw new Exception("ユーザーはまだ登録されていないかパスワードは間違っています");
						}
	        		}
					success = true;
				}
	        	catch(SQLException ex){
					ex.printStackTrace();
				}
	        	catch(Exception ex){
	        		errors.put(NAME_LOGIN, ex.getMessage());
				}
	        	finally {
	        		ConnectionDB.closeConnection();
	        	}
        	}
        }
        return user;
    }
}
