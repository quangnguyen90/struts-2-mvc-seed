package common.base;

import java.util.ArrayList;
import java.util.Map;

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
		
}