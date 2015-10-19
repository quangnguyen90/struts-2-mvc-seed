package common.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.domain.Gender;

import dao.dao.CityDAO;
import dao.domain.City;
import dao.domain.District;
import dao.mapper.CityMapper;

import dao.dao.DistrictDAO;
import dao.mapper.DistrictMapper;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import common.util.Consts;

import dao.domain.User;


public abstract class BaseAction extends ActionSupport
	implements SessionAware{

	private Map<String, Object> _session;
	protected ArrayList<String> arrErrorMessage;
	
	private static final long serialVersionUID = 1L;
	
	protected void showError(String errorMessage) {
		arrErrorMessage.add(errorMessage);
	}
	public BaseAction() {
		arrErrorMessage = new ArrayList<String>();
		_session = null;
	}		
	public User getCurrentUser() {
		return (User) _session.get(Consts.SESSION_LOING_MASTER_USER);
	}

	protected void setCurrentUser(User user) {
		if(_session!=null && user!=null){
			_session.put(Consts.SESSION_LOING_MASTER_USER, user);
		}
	}
	
	protected void delCurrentUser() {		
		_session.put(Consts.SESSION_LOING_MASTER_USER, null);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		_session = session;
	}

	public ArrayList<String> getArrErrorMessage() {
		return arrErrorMessage;
	}
	public void setArrErrorMessage(ArrayList<String> arrErrorMessage) {
		this.arrErrorMessage = arrErrorMessage;
	}
	
	private String successMessage;

	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	private static String nextPage = null;

	public static String getNextPage() {
		return nextPage;
	}
	public static void setNextPage(String nextPage) {
		BaseAction.nextPage = nextPage;
	}
	
	//==============================================================================================
	//City
	protected ArrayList<City> city;
	protected String citySelected;
	
	public ArrayList<City> getCity() {
		return city;
	}
	public void setCity(ArrayList<City> city) {
		this.city = city;
	}
	
	public String getCitySelected() {
		return citySelected;
	}
	public void setCitySelected(String citySelected) {
		this.citySelected = citySelected;
	}
	
	public void getCityList() {
		CityMapper cityMapper = new CityDAO();
		city = cityMapper.selectCityList();
	}
	
	//==============================================================================================
	// District
	protected String districtSelected;
	protected ArrayList<District> district;
	
	public ArrayList<District> getDistrict() {
		return district;
	}
	public void setDistrict(ArrayList<District> district) {
		this.district = district;
	}
	
	public String getDistrictSelected() {
		return districtSelected;
	}
	public void setDistrictSelected(String districtSelected) {
		this.districtSelected = districtSelected;
	}
	
	public void getDistrictList(int cityid) {
		DistrictMapper districtMapper = new DistrictDAO();
		district = districtMapper.selectDistrictFromCity(cityid);
	}
	
	//==============================================================================================
	// gender
	protected String genderSelected;	
	private List<Gender> genders = new ArrayList<Gender>();

	public String getGenderSelected() {
		return genderSelected;
	}
	public void setGenderSelected(String genderSelected) {
		this.genderSelected = genderSelected;
	}

		
	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public void createGenderList() {
		Gender genderMale = new Gender();
		genderMale.setGenderId(1);
		genderMale.setGenderName("Male");

		genders.add(genderMale);

		Gender genderFemale = new Gender();
		genderFemale.setGenderId(2);
		genderFemale.setGenderName("Female");

		genders.add(genderFemale);

		Gender genderOther = new Gender();
		genderOther.setGenderId(3);
		genderOther.setGenderName("Other");

		genders.add(genderOther);
	}
	//==============================================================================================
	// For load list
	private int offset = 1;
	public int count;
	protected int ItemPerPage = 2;
	protected int numberPage = offset + 1;
	protected String totalPage;

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getItemPerPage() {
		return ItemPerPage;
	}
	public void setItemPerPage(int itemPerPage) {
		ItemPerPage = itemPerPage;
	}
	
	public int getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
}