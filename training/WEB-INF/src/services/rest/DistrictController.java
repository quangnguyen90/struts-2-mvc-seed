package services.rest;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import dao.domain.District;
import dao.handler.DistrictHandler;

public class DistrictController extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private ArrayList<District> recordAs;
	private List<District> records;
	private String result;
    private String message;
	private int cityId; // Parameter from Ajax
	private int districtId;
	private String districtName;
	private boolean status;
	
	//======================================================================================================
	// TEST SERVICE JSON
	public void hello(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter  out = response.getWriter();
			out.println("yolo");
			out.flush();
		}catch(Exception e){
			
		}
	}
	
	// GET DISTRICT LIST
	public String getDistrictListAction() {
		DistrictHandler handler = new DistrictHandler();       
		try {
			// Fetch Data from District Table
			records=handler.selectDistrictFromCity(getCityId());
			result = "OK";
			
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			System.err.println(message);
		}
		return SUCCESS;
	}
	//======================================================================================================
	// GETTER _ SETTER
	public ArrayList<District> getRecordAs() {
		return recordAs;
	}

	public void setRecordAs(ArrayList<District> recordAs) {
		this.recordAs = recordAs;
	}
	
	public List<District> getRecords() {
		return records;
	}

	public void setRecords(List<District> records) {
		this.records = records;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
