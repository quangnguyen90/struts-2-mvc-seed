package models.actions.authAdmin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;

import models.actions.auth.RegisterAccountAction;

import org.apache.commons.io.FileUtils;
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
import dao.dao.UserDAO;
import dao.domain.MailConfig;
import dao.domain.User;
import dao.mapper.UserMapper;

@Results({
		@Result(name = "createUser", type = "tiles", location = "tiles.create_user"),
		@Result(name = "success", type = "tiles", location = "tiles.create_user"),
		@Result(name = "successex", type = "redirectAction", location = "create-user.html"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),

})
@ParentPackage("master_login")
public class CreateUserAction extends BaseAction implements ModelDriven<User> {
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
	// ======================================================================================================
	// CREATE USER
	@Action("/create-user")
	public String createUser() {
		refeshCDG();
		return "createUser";
	}
	public void refeshCDG(){
		getCityList();
		getDistrictList(Integer.parseInt(getCitySelected() == null ? "1" : getCitySelected()));
	}
	@Action("/create-user-action")
	public String createUserAction()
	{
		try {
			String type = ServletActionContext.getRequest().getMethod();
			
			if(type.toUpperCase() == "GET")
			{
				return "successex";
			}
			// ==============================================================================================
			// MMAKE DEFAULT PASSWORD AND PARSE TO MD5 CODE
			try {
				user.setPassword(ValidateUtil.MD5Encryption("123456"));
			}

			catch (NoSuchAlgorithmException a) {
				a.printStackTrace();
			}

			catch (UnsupportedEncodingException a) {
				a.printStackTrace();
			}
			user.setCity_Id(Integer.parseInt(getCitySelected()));
			user.setDistrict_Id(Integer.parseInt(getDistrictSelected()));
			user.setGender(Integer.parseInt(getGenderSelected()));
			
			// Check username, fullname, gender, email, telephone, city, district, address
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
			
			// SEND EMAIL
			// execute(user.getEmail(), 1);
			refeshCDG();
			setSuccessMessage("Create User OK");
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

				if (ValidateUtil.validateEmptyString(user.getTel()) == true) {
			showError("Telephone is required");
			check = false;
		}
		// =====================================================Validate LENGTH
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
	// ======================================================================================================
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	// ======================================================================================================
	// GETTER - SETTER
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
		CreateUserAction.errTimes = errTimes;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getGenderSelected() {
		return genderSelected;
	}
	public void setGenderSelected(String genderSelected) {
		this.genderSelected = genderSelected;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public static MailConfig getMailConfig() {
		return mailConfig;
	}
	public static void setMailConfig(MailConfig mailConfig) {
		CreateUserAction.mailConfig = mailConfig;
	}
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		CreateUserAction.properties = properties;
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
