package models.actions.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.dao.UserDAO;
import dao.dao.MailConfigDAO;
import dao.domain.User;
import dao.domain.MailConfig;
import dao.mapper.UserMapper;
import dao.mapper.MailConfigMapper;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.EmailValidator;
import common.util.ValidateUtil;

@Results({
		@Result(name = "forgotPasswordForm", type = "tiles", location = "tiles.forgot_password"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),
		@Result(name = "successCx", type = "redirectAction", location = "tiles.forgot_password"),
		@Result(name = "success", type = "tiles", location = "tiles.forgot_password"),
		@Result(name = "successEx", type = "tiles", location = "error.jsp"),
		@Result(name = "errorEx", type = "tiles", location = "tiles.forgot_password"),
		@Result(name = "errorRx", type = "tiles", location = "error.jsp"),
		@Result(name = "errorPx", type = "tiles", location = "error.jsp") })
@ParentPackage("master_tiles")
public class ForgotPasswordAction extends BaseAction implements
		ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(ForgotPasswordAction.class);
	private static final String charset = "!@#$%^&*()" + "0123456789"
			+ "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private User user = new User();
	private String email, passWord, temporaryPassword, username;
	private static int errTimes = 0;
	private String to;
	private int typeId;
	private static EmailValidator emv = new EmailValidator();

	public static int getErrTimes() {
		return errTimes;
	}

	public static void setErrTimes(int errTimes) {
		ForgotPasswordAction.errTimes = errTimes;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getTypeId() {
		return typeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	// --- mail variable------
	private static MailConfig mailConfig = new MailConfig();

	static Properties properties = new Properties();

	public static MailConfig getMailConfig() {
		return mailConfig;
	}

	public static void setMailConfig(MailConfig mailConfig) {
		ForgotPasswordAction.mailConfig = mailConfig;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		ForgotPasswordAction.properties = properties;
	}

	public String getUsername() {
		// userID = ServletActionContext.getRequest().getParameter("userID");
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Action("/forgot-password")
	public String forgotPassword() {
		return "forgotPasswordForm";
	}

	@Action("/forgot-password-action")
	public String forgotPasswordAction() {
		try {
			String type = ServletActionContext.getRequest().getMethod();

			if (type.toUpperCase() == "GET") {
				return "successCx";
			}
			user.setUsername(user.getUsername());
			user.setEmail(user.getEmail());

			if (validateInputField() == false) {
				return "errorEx";
			}

			updateTemporaryPassword();
			UserMapper userMapper = new UserDAO();
			user = userMapper.retrievePassword(user);

			if (user == null) {
				return ERROR;
			}
			// SEND EMAIL
			execute(user.getEmail(), 3);
			setSuccessMessage("Your temporary password is sent to your email: "
					+ user.getEmail() + " . Please check your email");
			return SUCCESS;

		} catch (Exception e) { // TODO: handle exception
			log.error(e.getMessage());
			return ERROR;
		}

	}

	// ---------------SEND MAIL
	public void execute(String sTo, int sTypeId) throws IOException {
		setTo(sTo);
		setTypeId(sTypeId);

		MailConfigMapper mailConfigMapper = new MailConfigDAO();
		mailConfig = mailConfigMapper.selectMailConfigByID(typeId);

		properties.put("mail.smtp.host", mailConfig.getSmtpHost());
		String socketPort = (" " + mailConfig.getSmtpSocketPort()).trim();
		String socketClass = (" " + mailConfig.getSmtpSocketClass()).trim();

		if (ValidateUtil.validateEmptyString(socketPort) == false
				&& ValidateUtil.validateEmptyString(socketClass) == false) {
			properties.put("mail.smtp.socketFactory.port",
					mailConfig.getSmtpSocketPort());
			properties.put("mail.smtp.socketFactory.class",
					mailConfig.getSmtpSocketClass());
		}
		properties.put("mail.smtp.auth", mailConfig.getSmtpAuth());
		properties.put("mail.smtp.port", mailConfig.getSmtpPort());
		properties.put("mail.smtp.password", mailConfig.getPassword());

		try {
			if (ValidateUtil.validateEmptyString(socketPort) == false
					&& ValidateUtil.validateEmptyString(socketClass) == false) {
				Session session = Session.getDefaultInstance(properties,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(mailConfig
										.getFrom(), mailConfig.getPassword());
							}
						});

				// encording utf8 - subject
				byte[] pSubject = mailConfig.getSubject().getBytes("UTF-8");
				String subjectValue = new String(pSubject, "UTF-8"); // encording
																		// utf8
																		// -
																		// subject
				String iBody = mailConfig.getBody();
				byte[] pBody = iBody.getBytes("UTF-8");
				String bodyValue = new String(pBody, "UTF-8");

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(mailConfig.getFrom()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
				message.setSubject(subjectValue);
				message.setContent(
						bodyValue
								+ user.getTemporaryPassword()
								+ " <br><a href='http://127.0.0.1:8080/training/views/auth/resetPassword.jsp?username="
								+ user.getUsername() + "'>Click here:</a>",
						"text/html; charset=utf-8");
				Transport.send(message);

			} else { // Get the default Session object.
				Session session = Session.getDefaultInstance(properties, null);

				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(mailConfig.getFrom()));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));

				// Set Subject: header field
				message.setSubject(mailConfig.getSubject(), "UTF-8");

				// get username in from
				String[] fromItems = mailConfig.getFrom().split("@");
				String username = fromItems[0];

				// Now set the actual message
				message.setContent(
						mailConfig.getBody()
								+ user.getTemporaryPassword()
								+ " <br><a href='http://127.0.0.1:8080/training/views/auth/resetPassword.jsp?username="
								+ user.getUsername() + "'>Click here:</a>",
						"text/html; charset=utf-8");
				Transport transport = session.getTransport("smtp");
				transport.connect(mailConfig.getSmtpHost(), username,
						mailConfig.getPassword());
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			}

		} catch (Exception e) {
			setErrTimes(errTimes + 1);
			if (errTimes > 1) {
				log.error("Send mail reset password fail for "
						+ user.getUsername() + " with email address: " + to
						+ " times 2.");
				setErrTimes(0);
			} else {
				log.error("Send mail reset password fail for "
						+ user.getUsername() + " with email address: " + to
						+ " times 1.");
				execute(user.getEmail(), 3);
			}
		}
	}

	// Now set the actual message

	// create random temp password save in db
	public String updateTemporaryPassword() { // save random temp password
		String random = getRandomString(10);
		user.setTemporaryPassword(random);
		UserMapper userMapper = new UserDAO();
		int results = userMapper.updateTemporaryPassword(user);

		// random = String.valueOf(userMapper.updateTemporaryPassword(user));
		if (results == 0) {
			return ERROR;
		}
		return SUCCESS;
	}

	// get ramdom string
	public String getRandomString(int lengh) {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= lengh; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	// forgot password
	private String password, confirmNewPassword;

	@Action("/reset-password-action")
	public String resetPasswordAction() {
		// GET PASSWORD AND PARSE TO MD5 CODE
		password = user.getNewPassword();
		confirmNewPassword = user.getConfirmNewPassword();
		try {
			if (ValidateUtil.validateEmptyString(password) == false
					&& ValidateUtil.validateEmptyString(confirmNewPassword) == false) {
				user.setNewPassword(ValidateUtil.MD5Encryption(password));
				user.setConfirmNewPassword(ValidateUtil
						.MD5Encryption(confirmNewPassword));
			}
		}

		catch (NoSuchAlgorithmException a) {
			a.printStackTrace();
		}

		catch (UnsupportedEncodingException a) {
			a.printStackTrace();
		}

		try {
			if (validateInputField1() == false) {
				return "errorRx";
			}

			UserMapper userMapper = new UserDAO();
			int cons = userMapper.updatePassword(user);

			if (cons == 0) {
				return "errorPx";
			}
			setSuccessMessage("Reset password OK");
			return "successEx";
		} catch (Exception e) { // TODO: handle exception
			return "errorPx";
		}

	}

	/*
	 * Validate input input form return message, true or false
	 */
	public boolean validateInputField() {
		boolean check = true;
		// validate email
		if (ValidateUtil.validateEmptyString(user.getEmail()) == true) {
			showError("Email is required");
			check = false;
		}
		// validate userID
		if (ValidateUtil.validateEmptyString(user.getUsername()) == true) {
			showError("Username is required");
			check = false;
		}
		// validate length userID
		if (ValidateUtil.validateLengthOfUserName(user.getUsername()) == false) {
			showError("Username must have 6-25 characters");
			check = false;
		}
		// validate email standard
		if (emv.validate(user.getEmail()) == false) {
			showError("Email is invalid");
			check = false;
		}
		// validate email matching userID
		if (ValidateUtil.validateEmailMatchUserName(user) == false) {
			showError("Email & Username do not match");
			check = false;
		}

		return check;
	}

	//
	public boolean validateInputField1() {
		boolean check = true;

		// validate temporary password
		if (ValidateUtil.validateTemporaryPassword(user.getTemporaryPassword()) == false) {
			showError("Temporary password is invalid");
			check = false;
		} // validate new password
		if (ValidateUtil.validateEmptyString(user.getNewPassword()) == true) {
			showError("New password is required");
			check = false;
		}
		// validate confirm new password
		if (ValidateUtil.validateEmptyString(user.getConfirmNewPassword()) == true) {
			showError("Confirm new password is required");
			check = false;
		} // validate Length Of Password
		if (ValidateUtil.validateLengthOfPassword(user.getNewPassword()) == false) {
			showError("Length of password must have 6-50 characters");
			check = false;
		} // validate new password and confirm new password
		if (ValidateUtil.validateConfirmPassword(user.getNewPassword(),
				user.getConfirmNewPassword()) == false) {
			showError("New password & Confirm new password do not match");
			check = false;
		}

		return check;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
