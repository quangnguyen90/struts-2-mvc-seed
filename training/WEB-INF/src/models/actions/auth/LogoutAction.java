package models.actions.auth;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import common.base.BaseAction;

@Results({
	@Result(name = "success", type = "redirectAction", location = "home.html"),
	@Result(name = "error", type = "tiles", location = "tiles.error"),
	
})
@ParentPackage("master_login")
public class LogoutAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//======================================================================================================
	// LOG OUT CONSUMER
	/**
	 * DELETE CURRENT SESSION
	 * */
	@Action("/logout")
	public String logout() {
		try{
			delCurrentUser();
			return SUCCESS;
		}
		catch(Exception e){
			return ERROR;
		}
	}
}
