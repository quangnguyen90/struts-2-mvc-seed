package models.actions.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.ValidateUtil;

@Results({
		@Result(name = "changePasswordFrom", type = "tiles", location = "tiles.change_password"),
		@Result(name = "error", type = "tiles", location = "tiles.change_password"),
		@Result(name = "success", type = "tiles", location = "tiles.change_password"),
		@Result(name = "successEx", type = "redirectAction", location = "tiles.change_password"),
		@Result(name = "successCx", type = "redirectAction", location = "home.html"),
})
@ParentPackage("master_login")
public class ChangePasswordAction extends BaseAction implements ModelDriven<User> {
	protected static Logger log = Logger.getLogger(ChangePasswordAction.class);
	private static final long serialVersionUID = 1L;
	private String username;
	private String password, confirmNewPassword, newPassword;
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		username = ServletActionContext.getRequest().getParameter("username");
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// ======================================================================================================
	// LOAD CHANGE PASSWORD FORM
	@Action("/change-password")
	public String changePassword() {
		return "changePasswordFrom";
	}

	// ======================================================================================================
	// UPDATE PASSWORD
	@Action("/change-password-action")
	public String changePasswordAction() {
		try {
			String type = ServletActionContext.getRequest().getMethod();
			
			if(type.toUpperCase() == "GET")
			{
				return "successCx";
			}
			
			user.setUsername(getCurrentUser().getUsername());
			password = user.getPassword();
			newPassword = user.getNewPassword();
			confirmNewPassword = user.getConfirmNewPassword();
			
			try {
				if (ValidateUtil.validateEmptyString(password) == false &&
					ValidateUtil.validateEmptyString(newPassword) == false &&
					ValidateUtil.validateEmptyString(confirmNewPassword) == false) 
				{
					user.setPassword(ValidateUtil.MD5Encryption(password));
					user.setNewPassword(ValidateUtil.MD5Encryption(newPassword));
					user.setConfirmNewPassword(ValidateUtil.MD5Encryption(confirmNewPassword));
				}
			}

			catch (NoSuchAlgorithmException a) {
				a.printStackTrace();
			}

			catch (UnsupportedEncodingException a) {
				a.printStackTrace();
			}

			if (validateInputField() == false) {
				return ERROR;
			}
			UserMapper userMapper = new UserDAO();
			User userResult = userMapper.getUserById(getCurrentUser().getUsername());
			int cons = userMapper.updatePassword(user);
			if (cons == 0) {
				showError("Change password error. Try again");
				return ERROR;
			}
			if(userResult == null){
				showError("Update password error. Try again");
				return ERROR;
			}
			// REFRESH SESSION
			delCurrentUser();
			user.setUsername(userResult.getUsername());
			user.setPassword(user.getNewPassword());
			user.setFullname(userResult.getFullname());
			setCurrentUser(userResult);
			setSuccessMessage(getText("Change password OK"));
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Edit password error. Try again");
			return ERROR;
		}
	}

	// ======================================================================================================
	// VALIDATE INPUT FIELD
	public boolean validateInputField() {
		boolean check = true;

		// ===============Validate matching password & user===============
		if (ValidateUtil.validateOldPassword(user) == false) {
			showError(getText("Current password is incorrect"));
			check = false;
		}

		if (ValidateUtil.validateEmptyString(password) == true) {
			showError(getText("Current password must not be empty"));
			check = false;
		}
		if (ValidateUtil.validateEmptyString(newPassword) == true) {
			showError(getText("New password must not be empty"));
			check = false;
		}
		if (ValidateUtil.validateLengthOfPassword(password) == false) {
			showError(getText("Length of current password is incorrect, must from 6-50 characters"));
			check = false;
		}

		if (ValidateUtil.validateLengthOfPassword(newPassword) == false) {
			showError(getText("Length of new password is incorrect, must from 6-50 characters"));
			check = false;
		}

		if (ValidateUtil.validateLengthOfPassword(confirmNewPassword) == false) {
			showError(getText("Length of confirm password is incorrect, must from 6-50 characters"));
			check = false;
		}

		if (ValidateUtil.validateConfirmPassword(newPassword, confirmNewPassword) == false) {
			showError(getText("New password & confirm password is not match"));
			check = false;
		}
		return check;
	}

	// ======================================================================================================
	// GET MODEL FOR CONSUMER
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
