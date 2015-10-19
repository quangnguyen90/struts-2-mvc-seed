package models.actions.authAdmin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

@Results({
		@Result(name = "success", type = "tiles", location = "tiles.list_user"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),

})
@ParentPackage("master_login")
public class ListUserAction extends BaseAction implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private List<User> userResults;
	private ArrayList<String> lstPage;
	private String contextPath = null;
	private UserMapper userMapper = new UserDAO();
	private User user = new User();
	private static final String COUNT_STRING = "count_string";
	
	// ======================================================================================================
	@Action("/list-user")
	public String listUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		contextPath = request.getContextPath();
		try {
			if (request.getParameter("numberPage") != null) 
			{
				this.setOffset(Integer.parseInt(request.getParameter("numberPage")));
			}
			this.setCount(userMapper.countForSearch());
			userResults = userMapper.getAllUsers(this.getItemPerPage(),((this.getOffset() - 1) * this.getItemPerPage()));
			if(userResults.size() == 0){
				return "error";
			}
			if (count % ItemPerPage == 0) {
				numberPage = count / ItemPerPage;
			} else {
				numberPage = count / ItemPerPage + 1;
			}
			totalPage = "" + numberPage;

			// Store the count
			session.setAttribute(COUNT_STRING, this.getCount());
			// Loop for page number
			lstPage = new ArrayList<String>();
			for (int y = 0; y < numberPage; y++) {
				lstPage.add("" + (y + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	// ======================================================================================================
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	// ======================================================================================================
	// GETTER _ SETTER
	public List<User> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<User> userResults) {
		this.userResults = userResults;
	}

	public ArrayList<String> getLstPage() {
		return lstPage;
	}

	public void setLstPage(ArrayList<String> lstPage) {
		this.lstPage = lstPage;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static String getCountString() {
		return COUNT_STRING;
	}
}
