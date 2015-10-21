package controllers.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.util.Consts;


public class LoginMasterInterceptor extends AbstractInterceptor implements SessionAware{
	private static final long serialVersionUID = 1L;
	public void init() {
		System.out.println("Initializing MyLoggingInterceptor...");
	}
	public void destroy () {
		System.out.println("Destroying MyLoggingInterceptor...");
	}

	private Map<String, Object> _session;
	@Override
	public void setSession(Map<String, Object> session) {
		_session = session;
	}
	public String intercept(ActionInvocation invocation) throws Exception {

		UserMapper userMapper = new UserDAO();

		_session = ActionContext.getContext().getSession();
		User user  = (User)_session.get(Consts.SESSION_LOING_MASTER_USER);
		if ( user != null ){
			User user_result = userMapper.select(user);

			if ( null != user_result && user.getPassword().equals(user_result.getPassword()) ){
				return invocation.invoke();
			} else {
				if ( invocation.getAction() instanceof ValidationAware){
					((ValidationAware) invocation.getAction()).addActionError( "username & password do not match");
				}
				return "master_login";
			}
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			 String userId = request.getParameter("user_id");
			 String password = request.getParameter("password");
				 if(userId==null||password == null){
					 return "master_login";
				 }
	
				 user=new User();
				 user.setUsername(userId);
				 user.setPassword(password);
	
				User user_result = userMapper.select(user);
				if(user_result !=null){
					_session.put(Consts.SESSION_LOING_MASTER_USER, user_result);
					return invocation.invoke();
				}
				return "master_login";
		}
			 
	}

}