package common.base;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class CustBaseAction extends ActionSupport
						implements SessionAware{

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	//HoangNM add
	public static final String ERROR_EX = "errorEx";//error exception,some function return it
	protected ArrayList<String> arrErrorMessage;
	public ArrayList<String> getArrErrorMessage() {
		return arrErrorMessage;
	}

	public void setArrErrorMessage(ArrayList<String> arrErrorMessage) {
		this.arrErrorMessage = arrErrorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}


	protected String successMessage;
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public CustBaseAction(){
		successMessage = null;
		arrErrorMessage = new ArrayList<String>();
	}
	
	protected void showError(String errorMessage) {
		arrErrorMessage.add(errorMessage);
	}
	
	
	protected void showSuccess(String successMessage) {
		this.successMessage = successMessage;
	}
}
