package models.actions.authAdmin;

import models.actions.auth.UpdateAccountAction;

import org.apache.log4j.Logger;
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
		@Result(name = "success", type = "redirectAction", location = "list-user.html"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),
})
@ParentPackage("master_login")
public class REEDUserAction extends BaseAction implements ModelDriven<User> {
	/**
	 * 
	 */
	protected static Logger log = Logger.getLogger(UpdateAccountAction.class);
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private String userId;

	// ======================================================================================================
	@Action("/remove-user")
	public String removeUserAction() {
		try {
			// REMOVE USER
			UserMapper userMapper = new UserDAO();
			int cons = userMapper.deleteUser(getUserId());
			if (cons == 0) {
				showError("Error while remove user. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while delete user. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Action("/enable-user")
	public String enableUserAction() {
		try {
			// ENABLE USER
			UserMapper userMapper = new UserDAO();
			int cons = userMapper.enableUser(getUserId());
			if (cons == 0) {
				showError("Error while enable user. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while active user. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Action("/disable-user")
	public String disableUserAction() {
		try {
			// DISABLE USER
			UserMapper userMapper = new UserDAO();
			int cons = userMapper.disableUser(getUserId());
			if (cons == 0) {
				showError("Error while disable usser. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while disactive user. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Action("/reset-user-password")
	public String resetUserPasswordAction() {
		try {
			// RESET USER PASSWORD
			UserMapper userMapper = new UserDAO();
			int cons = userMapper.resetUserPassword(getUserId());
			if (cons == 0) {
				showError("Error while reseting user password info. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while  reseting user password. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	// ======================================================================================================
	// GETTER _ SETTER
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
