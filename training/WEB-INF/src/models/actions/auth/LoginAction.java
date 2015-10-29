package models.actions.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import models.actions.auth.recaptcha.ReCaptchaImpl;
import models.actions.auth.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.ValidateUtil;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

import dao.dao.NewsDAO;
import dao.domain.News;
import dao.mapper.NewsMapper;

@Results({
		@Result(name = "success", type = "redirectAction", location = "home.html"),
		@Result(name = "loginLoad", type = "tiles", location = "tiles.login"),
		@Result(name = "error", type = "tiles", location = "tiles.login"),
		@Result(name = "listUser", type = "redirectAction", location = "list-user.html"),
		@Result(name = "successCx", type = "redirectAction", location = "login.html"),
		@Result(name = "listNews", type = "redirectAction", location = "list-news.html") })
@ParentPackage("master_tiles")
public class LoginAction extends BaseAction implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(LoginAction.class);
	private User user = new User();
	private String message, idmessage;
	private int failCount;
	private String username;
	
	// ======================================================================================================
	// LOAD LOGIN FORM
	@Action("/login")
	public String login() {
		return "loginLoad";
	}
	
	// ======================================================================================================
	// LOGIN USER
	/**
	 * execute action login,check user exist?
	 * 
	 * @param null
	 * @return success or error (String)
	 * 
	 */
	@Action("/login_action")
	public String execute() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String type = ServletActionContext.getRequest().getMethod();

		if (type.toUpperCase() == "GET") {
			return "successCx";
		}

		try {
			user.setPassword(ValidateUtil.MD5Encryption(user.getPassword()));
		}

		catch (NoSuchAlgorithmException a) {
			a.printStackTrace();
		}

		catch (UnsupportedEncodingException a) {
			a.printStackTrace();
		}

		try {
			if (validateInputField() == false) {
				if (session.getAttribute("failCount") != null) {
					failCount = Integer.parseInt(session.getAttribute(
							"failCount").toString());
				}
				failCount++;
				session.setAttribute("failCount", failCount);
				return ERROR;
			}
			UserMapper userMapper = new UserDAO();
			User userResult = userMapper.select(user);
			if (userResult == null) {
				if (session.getAttribute("failCount") != null) {
					failCount = Integer.parseInt(session.getAttribute(
							"failCount").toString());
				}
				failCount++;
				session.setAttribute("failCount", failCount);
				showError(getText("User is not exist"));
				return ERROR;
			}
			user.setUsername(userResult.getUsername());
			user.setFullname(userResult.getFullname());
			setCurrentUser(userResult);
			session.setAttribute("failCount", null);
			setMessage(getText("welcome ") + user.getFullname());
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			showError("Error while login. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	// VALIDATE INPUT FIELD
	/**
	 * validate input field,if error return false else return true
	 * 
	 * @param null
	 * @return true or false (boolean)
	 * 
	 */
	public boolean validateInputField() {
		boolean check = true;
		if (user.getUsername() == null
				|| user.getUsername().trim().length() == 0) {
			showError(getText("Username is required"));
			check = false;
		}

		if (user.getPassword() == null
				|| user.getPassword().trim().length() == 0) {
			showError(getText("Password is required"));
			check = false;
		}

		if (ValidateUtil.validateNameStandard(user.getUsername()) == false) {
			showError(getText("Username is only allow [a-z]/[A-Z]/[0-9]"));
			check = false;
		}

		if (ValidateUtil.validateLengthOfUserName(user.getUsername()) == false) {
			showError(getText("Username must be 6-25 characters"));
			check = false;
		}

		if (ValidateUtil.validateLengthOfPassword(user.getPassword()) == false) {
			showError(getText("Password must be 6-50 characters"));
			check = false;
		}
		if (ValidateUtil.validateActiveUser(user.getUsername()) == true) {
			showError(getText("Account must be actived"));
			check = false;
		}
		check = Captcha();

		return check;
	}

	// ======================================================================================================
	// SHOW CAPTCHA
	/**
	 * Show CAPTCHA
	 * 
	 * */
	private boolean Captcha() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey("6Lf4W98SAAAAAHfcJzLW6bf3oINtbE12J1jwIpXD");

		if (request.getParameter("recaptcha_challenge_field") == null) {
			return true;
		}
		String challenge = request.getParameter("recaptcha_challenge_field");
		String uresponse = request.getParameter("recaptcha_response_field");
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
				challenge, uresponse);
		if (reCaptchaResponse.isValid()) {
			showError(getText("Captcha code is correct"));
			return true;
		} else {
			showError(getText("Captcha code is incorrect"));
			return false;
		}
	}
	// ======================================================================================================
	// GETTER _ SETTER
	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(String idmessage) {
		this.idmessage = idmessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	// ======================================================================================================
	// GET MODEL FOR CONSUMER
	@Override
	public User getModel() {
		return user;
	}
}
