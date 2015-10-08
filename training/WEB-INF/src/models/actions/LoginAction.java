package models.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

@Results({
	 @Result(name="success", type="tiles" ,location="tiles.welcome"),
	  @Result(name="loginLoad" ,  location="/views/auth/login.jsp"),
	  @Result(name="error" ,  location="/views/auth/login.jsp"),
	  @Result(name="errorEx" ,  type="tiles" ,location="tiles.error")
	})
@ParentPackage("master_tiles")
public class LoginAction extends BaseAction implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;

	private User user = new User();
	
	private String message;
	
	/** Set the locale language */
	public LoginAction() {
		
	}
	@Override
	public User getModel() {
		return user;
	}
	@Action("/master/login")
	public String login() {
		return "loginLoad";
	}	
	/**
	 * validate input field,if error return false else return true
	 * @param null
	 * @return true or false (boolean)
	 * 
	*/
	public boolean validateInputField(){
		boolean check = true;	
		if(user.getUsername() == null || user.getUsername().trim().length()==0){
			showError(getText("error.username.require"));
			check = false;
		}
		if(user.getPassword() == null || user.getPassword().trim().length()==0){
			showError(getText("error.password.require"));
			check = false;
		}
		return check;
	}
	
	/**
	 * execute action login,check user exist?
	 * @param null
	 * @return success or error (String)
	 * 
	*/
	@Action("/master/login_action")
	public String execute() {
		try{
			if(!validateInputField()){
				return ERROR;
			}
			UserMapper userMapper = new UserDAO();
			User userResult = userMapper.select(user);
			if(userResult == null){
				showError(getText("error.notexists2"));
				return ERROR;
			}
			user.setUsername(userResult.getUsername());
			user.setFullname(userResult.getFullname());
			setCurrentUser(userResult);
			setMessage(getText("welcome") +user.getFullname());
			return SUCCESS;
		}catch(Exception e) {
			showError(e.getMessage());
			return ERROR;
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
