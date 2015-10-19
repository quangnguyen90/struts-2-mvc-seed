package models.actions.auth;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.EmailValidator;
import common.util.ValidateUtil;

import dao.dao.MailConfigDAO;
import dao.dao.UserDAO;
import dao.domain.MailConfig;
import dao.domain.User;
import dao.mapper.MailConfigMapper;
import dao.mapper.UserMapper;
//import com.sun.mail.smtp.SMTPAddressFailedException;

//import javax.mail.SendFailedException;

@Results({
		@Result(name = "registerForm", type = "tiles", location = "tiles.register"),
		@Result(name = "error", type = "tiles", location = "tiles.register"),
		@Result(name = "successex", type = "redirectAction", location = "register.html"),
		@Result(name = "success", type = "redirectAction", location = "home.html"),
})
@ParentPackage("master_tiles")
public class RegisterAccountAction extends BaseAction implements ModelDriven<User> {
	protected static Logger log = Logger.getLogger(RegisterAccountAction.class);
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private String birthday,genderSelected;
	// For check & send mail
	private String to;
	private int typeID;
	private static int errTimes=0;
	private static EmailValidator emV = new EmailValidator();
	private static MailConfig mailConfig = new MailConfig();
	static Properties properties = new Properties();
	// For Save images
	private File myAvatar;
	private String myAvatarFileName, myAvatarContentType;
	// For md5 password
	private String password, confirmPassword;
	// ======================================================================================================
	// REGISTER USER 
	@Action("/register")
	public String register() {
		refeshCDG();
		return "registerForm";
	}
	public void refeshCDG(){
		getCityList();
		getDistrictList(Integer.parseInt(getCitySelected() == null ? "1" : getCitySelected()));
		createGenderList();
	}
	@Action("/register-action")
	public String registerAction(){
		try {
			String type = ServletActionContext.getRequest().getMethod();
			
			if(type.toUpperCase() == "GET")
			{
				return "successex";
			}
			//refeshCDG();
			// ==============================================================================================
			// GET PASSWORD AND PARSE TO MD5 CODE
			password = user.getPassword();
			confirmPassword = user.getConfirmPassword();
			try {
				if (ValidateUtil.validateEmptyString(password) == false
						&& ValidateUtil.validateEmptyString(confirmPassword) == false) {
					user.setPassword(ValidateUtil.MD5Encryption(password));
					user.setConfirmPassword(ValidateUtil
							.MD5Encryption(confirmPassword));
				}
			}

			catch (NoSuchAlgorithmException a) {
				a.printStackTrace();
			}

			catch (UnsupportedEncodingException a) {
				a.printStackTrace();
			}
			user.setCityId(Integer.parseInt(getCitySelected()));
			user.setDistrictId(Integer.parseInt(getDistrictSelected()));
			user.setGender(Integer.parseInt(getGenderSelected()));
			
			// Check username, password, confirm password, fullname, gender, email, telephone, city, district, address
			if (validateInputField() == false) {
				refeshCDG();
				return ERROR;
			}
			// ==============================================================================================
			// CHECK BIRTHDAY
			// INSERT USER WHEN USER'S BIRTHDAY != NULL || != ""
			if (ValidateUtil.validateEmptyString(getBirthday()) == false) {
				if (ValidateUtil.validateDateFormat(getBirthday()) == false) {
					showError("Birthday is invalid, allow dd/mm/yyyy");
					refeshCDG();
					return ERROR;
				}
				// PARSE STRING TO DATE
				try {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateFormatter.parse(getBirthday());
					user.setDob(date);
				} catch (Exception ex) {
					showError("Error while parsing birthday. Try again");
					refeshCDG();
					return ERROR;
				}
			}
			// ==============================================================================================
			// INSERT USER WHEN USER'S BIRTHDAY == NULL || == ""
			else {
				try {
					user.setDob(null);
				} catch (Exception ex) {
					showError("Error while checking birthday. Try again");
					refeshCDG();
					return ERROR;
				}
			}
			// ==============================================================================================
			// CHECK AVATAR
			if (getMyAvatar() != null) {
				if(ValidateUtil.fileExtention(myAvatarContentType)=="")
				 {
					showError("Image only allow: jpg, jpeg, png");
					refeshCDG();
					return ERROR;
				}
				else
				{
					if(uploadFileToMyFolder()==false){
						showError("Error while upload file. Try again");
						refeshCDG();
						return ERROR;
					}
				}
			} else {
				user.setAvatar("/assets/img/users-img/images.png");
			}
			// ========================================================================================
			// set usertype
			user.setUserType(2);
			// ========================================================================================
			// INSERT USER
			UserMapper userMapper = new UserDAO();
			int userResult = userMapper.insertUser(user);
			if (userResult == 0) {
				showError("Error while register. Try again");
				refeshCDG();
				return ERROR;
			}
			// ========================================================================================
			// AUTO LOGIN AFTER REGSTER SUCCESS
			// Must create new mapper, new user instance to call function because the previous session is closed
			UserMapper userMapper2 = new UserDAO();
			User userDB = userMapper2.select(user);
			if (userDB == null) {
				showError("Error while login after register. Refesh & try again");
				refeshCDG();
				return ERROR;
			}
			// ========================================================================================
			// SET CURRENT USER
			user.setUsername(userDB.getUsername());
			user.setFullname(userDB.getFullname());
			setCurrentUser(userDB);
			// Load Top 8 news
			//loadParentDealCategory();
			//getCityList2();

			// SEND EMAIL
			// execute(user.getEmail(), 1);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			showError("Error while create account. Try again");
			refeshCDG();
			return ERROR;
		}
	}
	// ======================================================================================================
	// Upload Image
	public boolean uploadFileToMyFolder() {
		boolean check = true;
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = "/assets/img/users-img";
			// getting the path to where the images will be uploaded
			String filePath = servletContext.getRealPath(path);

			File uploadDir = new File(filePath);
			// if the folder does not exits, creating it
			if (uploadDir.exists() == false) {
				uploadDir.mkdirs();
			}
			user.setAvatar(path + "/" + user.getUsername() + ValidateUtil.fileExtention(myAvatarContentType));
			FileUtils.copyFile(this.myAvatar, new File(filePath, user.getUsername()+ ValidateUtil.fileExtention(myAvatarContentType)));
			check = true;
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			addActionError(e.getMessage());
			check = false;
		}
		return check;
	}

	// ======================================================================================================
	// SHOW DISTRICT LIST WHEN CHOOSE CITY
	//@Action("/getDistrictList")
	/*public String change_city1() {
		try {
			String type = ServletActionContext.getRequest().getMethod();
			
			if(type.toUpperCase() == "GET")
			{
				return "successex";
			}
			
			if (myfile != null) {
				uploadFileToMyFolder();
			} else {
				user.setAvatarUrl("/view/common_view/images/AccountImages/images.jpg");
			}
			getCityList2();
			loadParentDealCategory();
			getCityList();
			getDistrictList(Integer.parseInt(getCitySelected()));
			createGenderList();
			return ERROR;

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}*/

	// ======================================================================================================
	// VALIDATE INPUT FIELD
	/**
	 * Validate input input form return message, true or false
	 */
	public boolean validateInputField() {
		boolean check = true;
		// =====================================================Validate EMPTY
		if (ValidateUtil.validateEmptyString(user.getEmail()) == true) {
			showError("Email is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(user.getFullname()) == true) {
			showError("Fullname is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(user.getUsername()) == true) {
			showError("Username is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(password) == true) {
			showError("Password is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(confirmPassword) == true) {
			showError("Confirm password is required");
			check = false;
		}
		if (ValidateUtil.validateEmptyString(user.getAddress()) == true) {
			showError("Address is required");
			check = false;
		}
		if (ValidateUtil.validateEmptyString(user.getTel()) == true) {
			showError("Telephone is required");
			check = false;
		}
		// =====================================================Validate LENGTH
		if (ValidateUtil.validateLengthOfPassword(password) == false) {
			showError("Password length: 6-50 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfFullname(user.getFullname()) == false) {
			showError("Fullname length: 6-50 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfAddress(user.getAddress()) == false) {
			showError("Address length: 10-100 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfPhoneNumber(user.getTel()) == false) {
			showError("Telephone length: 10-20 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfUserName(user.getUsername()) == false) {
			showError("Username length: 6-25 characters");
			check = false;
		}
		// =====================================================Validate
		// STANDARD
		if (emV.validate(user.getEmail()) == false) {
			showError("Email is invalid");
			check = false;
		}

		
		if (ValidateUtil.validateConfirmPassword(password, confirmPassword) == false) {
			showError("Password & Confirm password do not math");
			check = false;
		}

		if (ValidateUtil.validateNameStandard(user.getUsername()) == false) {
			showError("Username only allow a-z, A-Z, 0-9");
			check = false;
		}

		// =====================================================Validate EXIST
		if (ValidateUtil.validateUserExist(user.getUsername()) == false) {
			showError("Usernam is already exist");
			check = false;
		}

		if (ValidateUtil.validateUserEmailExist(user.getEmail()) == false) {
			showError("Email is already exist");
			check = false;
		}
		// =====================================================Validate not
		// choose city/district
		if (Integer.parseInt(getCitySelected()) == 0) {
			showError("City is required");
			check = false;
		}

		if (Integer.parseInt(getDistrictSelected()) == 0) {
			showError("District is required");
			check = false;
		}

		if (getGenderSelected() == "0") {
			showError("Gender is required");
			check = false;
		}

		return check;
	}
	
	// ===================================================== SEND MAIL
	public void execute(String sTo, int sTypeID) throws IOException {	      
		setTo(sTo);
		setTypeID(sTypeID);
		
		MailConfigMapper mailConfigMapper = new MailConfigDAO();
		mailConfig = mailConfigMapper.selectMailConfigByID(typeID);
		
		properties.put("mail.smtp.host", mailConfig.getSmtpHost());
		String socketPort=(" "+mailConfig.getSmtpSocketPort()).trim();
		String socketClass=(" "+mailConfig.getSmtpSocketClass()).trim();
		
		if(ValidateUtil.validateEmptyString(socketPort)==false&&ValidateUtil.validateEmptyString(socketClass)==false){
			properties.put("mail.smtp.socketFactory.port", mailConfig.getSmtpSocketPort());
			properties.put("mail.smtp.socketFactory.class",	mailConfig.getSmtpSocketClass());
		}
		properties.put("mail.smtp.auth", mailConfig.getSmtpAuth());
		properties.put("mail.smtp.port", mailConfig.getSmtpPort());
		properties.put("mail.smtp.password", mailConfig.getPassword());
        
		try {
			if(ValidateUtil.validateEmptyString(socketPort)==false&&ValidateUtil.validateEmptyString(socketClass)==false){
				Session session = Session.getDefaultInstance(properties,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(mailConfig.getFrom(), mailConfig.getPassword());
							}
						});
				
				// encording utf8 - subject
				byte[] pSubject = mailConfig.getSubject().getBytes("UTF-8");
				String subjectValue = new String(pSubject, "UTF-8");
				// encording utf8 - subject
				String iBody=mailConfig.getBody()+" Username: "+user.getUsername()+" - Password: "+password+" ";
				byte[] pBody = iBody.getBytes("UTF-8");
				String bodyValue = new String(pBody, "UTF-8");
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(mailConfig.getFrom()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
				message.setSubject(subjectValue);
				message.setText(bodyValue);
				Transport.send(message);
				
			}else{
				// Get the default Session object.
		           Session session = Session.getDefaultInstance(properties,null);
		           
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
	              String username=fromItems[0];
	              
	              // Now set the actual message
	              message.setText(mailConfig.getBody()+" Username: "+user.getUsername()+" - Password: "+password+" ", "UTF-8");
				Transport transport = session.getTransport("smtp");
				transport.connect(mailConfig.getSmtpHost(),username, mailConfig.getPassword());
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			}

		} catch (Exception e) {
			setErrTimes(errTimes + 1);
			if(errTimes>1){
				log.error("Send mail register user fail for "+user.getUsername()+" with email address: "+to+ " times 2.");
				setErrTimes(0);
			}
			else{
				log.error("Send mail register user fail for "+user.getUsername()+" with email address: "+to+ " times 1.");
				execute(user.getEmail(), 1);
			}
		}
	}

	// ======================================================================================================
	// GET MODEL FOR USER
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	// ======================================================================================================
	// GETTER - SETTER
	public String getGenderSelected() {
		return genderSelected;
	}
	public void setGenderSelected(String genderSelected) {
		this.genderSelected = genderSelected;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public static int getErrTimes() {
		return errTimes;
	}
	public static void setErrTimes(int errTimes) {
		RegisterAccountAction.errTimes = errTimes;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static MailConfig getMailConfig() {
		return mailConfig;
	}
	public static void setMailConfig(MailConfig mailConfig) {
		RegisterAccountAction.mailConfig = mailConfig;
	}
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		RegisterAccountAction.properties = properties;
	}
	public File getMyAvatar() {
		return myAvatar;
	}
	public void setMyAvatar(File myAvatar) {
		this.myAvatar = myAvatar;
	}
	public String getMyAvatarFileName() {
		return myAvatarFileName;
	}
	public void setMyAvatarFileName(String myAvatarFileName) {
		this.myAvatarFileName = myAvatarFileName;
	}
	public String getMyAvatarContentType() {
		return myAvatarContentType;
	}
	public void setMyAvatarContentType(String myAvatarContentType) {
		this.myAvatarContentType = myAvatarContentType;
	}
}
