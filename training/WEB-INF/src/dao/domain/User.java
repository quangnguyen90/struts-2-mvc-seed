package dao.domain;

import java.util.Date;

/**
 * 
 */
public class User {
	/*//////////////////// This area is to define members ////////////////////////////*/
	private String avatar,newPassword, username,password,fullname,address,email, tel, temporaryPassword, confirmPassword, confirmNewPassword;
	private Date dob;
	private int status = 1, userType, gender;
	private int city_Id; 
	private int district_Id ;
	
	public int getCity_Id() {
		return city_Id;
	}
	public void setCity_Id(int city_Id) {
		this.city_Id = city_Id;
	}
	public int getDistrict_Id() {
		return district_Id;
	}
	public void setDistrict_Id(int district_Id) {
		this.district_Id = district_Id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTemporaryPassword() {
		return temporaryPassword;
	}
	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	private City cityObject; 
	private District districtObject;
	
	public City getCityObject() {
		return cityObject;
	}
	public void setCityObject(City cityObject) {
		this.cityObject = cityObject;
	}
	public District getDistrictObject() {
		return districtObject;
	}
	public void setDistrictObject(District districtObject) {
		this.districtObject = districtObject;
	}

	
}