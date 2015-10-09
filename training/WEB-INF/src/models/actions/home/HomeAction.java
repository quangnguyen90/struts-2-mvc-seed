package models.actions.home;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import common.base.BaseAction;

@Results({
	 @Result(name="success", type="tiles" ,location="tiles.welcome"),
	 @Result(name="errorEx" ,  type="tiles" ,location="tiles.error")
	})
@ParentPackage("master_tiles")
public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String message;
	
	/** Set the locale language */
	public HomeAction() {
		
	}
	@Action("/welcome/home")
	public String welcome() {
		setMessage(getText("Welcome to Strut 2 mvc"));
		return "success";
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
