package models.actions.authAdmin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import models.actions.auth.UpdateAccountAction;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;

import common.base.BaseAction;
import common.util.MiscUtils;
import common.util.ValidateUtil;
import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

@Results({
		@Result(name = "editUser", type = "tiles", location = "tiles.edit_user"),
		@Result(name = "success", type = "tiles", location = "tiles.edit_user"),
		@Result(name = "error", type = "tiles", location = "tiles.edit_user"),
		@Result(name = "errorEx", type = "tiles", location = "tiles.error"),

})
@ParentPackage("master_login")
public class EditUserAction extends BaseAction implements ModelDriven<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(UpdateAccountAction.class);
	private User user = new User();
	private String citySelected, districtSelected, birthday;
	private String contextPath = null;
	private String userId;
	private String imgAURL;
	private File myAvatar;
	private String myAvatarFileName, myAvatarContentType, genderSelected;
	// ======================================================================================================
	// LOAD USER DETAIL
	@Action("/edit-user")
	public String editUser() {
		try {
			UserMapper userMapper = new UserDAO();
			user = userMapper.getUserById(getUserId());
			// Get user info
			if(user == null){
				showError("Error when getting profile info. Refresh & try again");
				// Get City, District, Gender List
				getCityList();
				getDistrictList(user.getCity_Id());
				createGenderList();
				
				// Show City, District, Gender by User
				citySelected = String.valueOf(user.getCity_Id());
				districtSelected = String.valueOf(user.getDistrict_Id());
				genderSelected = user.getGender()+"";
				
				return ERROR;
			}
						
			// Get City, District, Gender List
			getCityList();
			getDistrictList(user.getCity_Id());
			createGenderList();
			
			// Show City, District, Gender by User
			citySelected = String.valueOf(user.getCity_Id());
			districtSelected = String.valueOf(user.getDistrict_Id());
			genderSelected = user.getGender()+"";
			
			HttpServletRequest request = ServletActionContext.getRequest();
			imgAURL = user.getAvatar();
			contextPath = request.getContextPath();
			
			// convert date to string
			try{
				if(user.getDob()==null){
					birthday = null;
				}
				else{
					birthday = MiscUtils.convertDateToString(user.getDob(), "dd/MM/yyyy");
				}
			}
			catch (Exception e){
				log.error(e.getMessage());
				showError("Error while showing birthday");
				// Get City, District, Gender List
				getCityList();
				getDistrictList(user.getCity_Id());
				createGenderList();
				
				// Show City, District, Gender by User
				citySelected = String.valueOf(user.getCity_Id());
				districtSelected = String.valueOf(user.getDistrict_Id());
				genderSelected = user.getGender()+"";
				
				return ERROR;
			}
			// To show detail user info
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			showError("Error while showing account info. Try again");
			// Get City, District, Gender List
			getCityList();
			getDistrictList(user.getCity_Id());
			createGenderList();
			
			// Show City, District, Gender by User
			citySelected = String.valueOf(user.getCity_Id());
			districtSelected = String.valueOf(user.getDistrict_Id());
			genderSelected = user.getGender()+"";
			return ERROR;
		}
	}
	
	// EDIT USER
	@Action("/edit-user-action")
	public String editUserAction() {
		try {
			user.setUsername(this.getUserId());
			
			//LOAD USER INFO
			UserMapper userMapp = new UserDAO();
			User userResult = userMapp.getUserById(this.getUserId());
			if(userResult == null){
				showError("Error while getting user profile. Try again");
				return ERROR;
			}
			
			// ======================================================================================================
			if (validateInputField() == false) {
				return ERROR;
			}
			// ==============================================================================================
			// CHECK BIRTHDAY
			// SET USER'S DOB WHEN USER'S BIRTHDAY != NULL || != ""
			if (ValidateUtil.validateEmptyString(getBirthday()) == false) {
				// PARSE STRING TO DATE
				try {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateFormatter.parse(getBirthday());
					user.setDob(date);
				} catch (Exception ex) {
					showError("Error while updating birthday");
					return ERROR;
				}
				if (ValidateUtil.validateDateFormat(birthday) == false) {
					showError("Birthday is invalid");
					return ERROR;
				}
			}
			// ======================================================================================================
			// SET USER'S DOB WHEN USER'S BIRTHDAY = NULL || = ""
			else {
				try {
					user.setDob(null);
				} catch (Exception ex) {
					log.error(ex.getMessage());
					showError("Error while checking birthday. Try again");
					return ERROR;
				}
			}
			// ==============================================================================================
			// CHECK AVATAR			
			if (myAvatar != null) {
				if(ValidateUtil.fileExtention(myAvatarContentType)=="")
				 {
					showError("Image only allow: jpg, jpeg, png");
					//refeshCDG();
					return ERROR;
				}
				else
				{
					if(uploadFileToMyFolder()==false){
						showError("Error while upload file. Try again");
						//refeshCDG();
						return ERROR;
					}
				}
			} else {
				user.setAvatar(userResult.getAvatar());
			}
			
			user.setCity_Id(Integer.parseInt(getCitySelected()));
			user.setDistrict_Id(Integer.parseInt(getDistrictSelected()));
			user.setGender(Integer.parseInt(getGenderSelected()));
			// =======================================================================================
			// UPDATE USER
			UserMapper userMapper = new UserDAO();
			int cons = userMapper.updateUser(user);
			if (cons == 0) {
				showError("Error while edit profile info. Try again");
				return ERROR;
			}
			// =======================================================================================
			// GET USER INFO AFTER UPDATE
			UserMapper userMapper2 = new UserDAO();
			User userDB = userMapper2.getUserById(this.getUserId());
			if (userDB == null) {
				showError("Error while getting account info. Try again");
				return ERROR;
			}
			//======================= REFRESH ACCOUNT PAGE
			try {
				//setUserId(this.getUserId());
				// Get City, District, Gender list
				getCityList();
				getDistrictList(userDB.getCity_Id());
				createGenderList();
				
				//Get City, District, Gender of User
				citySelected = String.valueOf(userDB.getCity_Id());
				districtSelected = String.valueOf(userDB.getDistrict_Id());
				genderSelected = userDB.getGender()+"";
				
				HttpServletRequest request = ServletActionContext.getRequest();
				imgAURL = userDB.getAvatar();
				contextPath = request.getContextPath();
				
				// convert date to string
				try{
					if(userDB.getDob()==null){
						birthday = null;
					}
					else{
						birthday = MiscUtils.convertDateToString(userDB.getDob(), "dd/MM/yyyy");
						}
				}
				catch (Exception e){
					showError("Error while showing birthday");
					return ERROR;
				}
				
				setSuccessMessage("Edit OK");
				return SUCCESS;
			} catch (Exception e) {
				log.error(e.getMessage());
				showError("Error while showing profile info. Try again");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while updating profile info. Try again");
			return ERROR;
		}
	}
	
	public boolean uploadFileToMyFolder() {
		boolean check = true;
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = "/assets/img/users-img/";
			// getting the path to where the images will be uploaded
			String filePath = servletContext.getRealPath(path);

			File uploadDir = new File(filePath);
			// if the folder does not exits, creating it
			if (uploadDir.exists() == false) {
				uploadDir.mkdirs();
			}
			user.setAvatar(path + "/" + getCurrentUser().getUsername() + ValidateUtil.fileExtention(myAvatarContentType));
			FileUtils.copyFile(myAvatar, new File(uploadDir, getCurrentUser().getUsername() + ValidateUtil.fileExtention(myAvatarContentType)));
			check = true;
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			addActionError(e.getMessage());
			log.error(e.getMessage());
			check = false;
		}
		return check;
	}
	
	//============================================================= VALIDATE INPUT FIELD
	public boolean validateInputField() throws UnsupportedEncodingException {
		boolean check = true;
		// =====================================================Validate EMPTY
		if (ValidateUtil.validateEmptyString(user.getFullname()) == true) {
			showError("Fullname must not be empty");
			check = false;
		}
		
		if (ValidateUtil.validateEmptyString(user.getAddress()) == true) {
			showError("Address must not be empty");
			check = false;
		}
		
		if (ValidateUtil.validateEmptyString(user.getTel()) == true) {
			showError("Tel must not be empty");
			check = false;
		}
		// =====================================================Validate LENGTH

		if (ValidateUtil.validateLengthOfFullname(user.getFullname()) == false) {
			showError("Length of fullname is incorrect, only allow 6-50 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfAddress(user.getAddress()) == false) {
			showError("Length of address is incorrect, only allow 10-100 characters");
			check = false;
		}

		if (ValidateUtil.validateLengthOfPhoneNumber(user.getTel()) == false) {
			showError("Length of phone number is incorrect, only allow 10-20 characters");
			check = false;
		}
		// =====================================================Validate not
		// choose city/district
		if (Integer.parseInt(getCitySelected()) == 0) {
			showError("City must not bt empty");
			check = false;
		}

		if (Integer.parseInt(getDistrictSelected()) == 0) {
			showError("District must not be empty");
			check = false;
		}

		if (getGenderSelected() == "0") {
			showError("Gender must not be empty");
			check = false;
		}
		return check;
	}
		
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


	public String getCitySelected() {
		return citySelected;
	}


	public void setCitySelected(String citySelected) {
		this.citySelected = citySelected;
	}


	public String getDistrictSelected() {
		return districtSelected;
	}


	public void setDistrictSelected(String districtSelected) {
		this.districtSelected = districtSelected;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getContextPath() {
		return contextPath;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		//userId = ServletActionContext.getRequest().getParameter("userId");
		this.userId = userId;
	}


	public String getImgAURL() {
		return imgAURL;
	}


	public void setImgAURL(String imgAURL) {
		this.imgAURL = imgAURL;
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


	public String getGenderSelected() {
		return genderSelected;
	}


	public void setGenderSelected(String genderSelected) {
		this.genderSelected = genderSelected;
	}
}
