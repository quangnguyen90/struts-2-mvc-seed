package models.actions.home;

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
import dao.dao.NewsDAO;
import dao.domain.News;
import dao.mapper.NewsMapper;

@Results({
	 @Result(name="home", type="tiles" ,location="tiles.welcome"),
	 @Result(name="about", type="tiles" ,location="tiles.about"),
	 @Result(name="error" ,  type="tiles" ,location="tiles.error")
	})
@ParentPackage("master_tiles")
public class HomeAction extends BaseAction implements ModelDriven<News>{

	private static final long serialVersionUID = 1L;

	private String message;
	private List<News> newsResults;
	private List<News> top8NewsResults;
	private ArrayList<String> lstPage;
	private String contextPath = null;
	private String imgAURL;
	private NewsMapper newsMapper = new NewsDAO();
	private News news = new News();
	private static final String COUNT_STRING = "count_string";
	//=============================================================================================
	@Action("/home")
	public String welcome() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		contextPath = request.getContextPath();
		try {
			if (request.getParameter("numberPage") != null) 
			{
				this.setOffset(Integer.parseInt(request.getParameter("numberPage")));
			}
			this.setCount(newsMapper.countForSearchNewsOn());
			// GET ALL NEWS
			newsResults = newsMapper.getAllNewsOn(this.getItemPerPage(),((this.getOffset() - 1) * this.getItemPerPage()));
			if(newsResults.size() == 0){
				return "errorex";
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
			
			// GET 8 HOT NEWS
			top8NewsResults = newsMapper.get8NewsOn();
			if(top8NewsResults.size() == 0){
				return "errorex";
			}
			
			// GET THE LASTEST NEWS
			NewsMapper lastestNewsMapper = new NewsDAO();
			news = lastestNewsMapper.getTheLastestNews();
			if (news == null) {
				showError("Error while show the lastest news. Try again");
				return ERROR;
			}
			
			imgAURL = news.getAvatar();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		setMessage(getText("Welcome to Strut 2 mvc"));
		if(getCurrentUser()!= null){
			setMessage(getText("Welcome " + getCurrentUser().getUsername()));
		}
		return "home";
	}
	
	@Action("/about")
	public String about() {
		return "about";
	}	
	
	//=============================================================================================
	// GETTER _ SETTER
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<News> getNewsResults() {
		return newsResults;
	}

	public void setNewsResults(List<News> newsResults) {
		this.newsResults = newsResults;
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

	public NewsMapper getNewsMapper() {
		return newsMapper;
	}

	public void setNewsMapper(NewsMapper newsMapper) {
		this.newsMapper = newsMapper;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public static String getCountString() {
		return COUNT_STRING;
	}
	
	public List<News> getTop8NewsResults() {
		return top8NewsResults;
	}

	public void setTop8NewsResults(List<News> top8NewsResults) {
		this.top8NewsResults = top8NewsResults;
	}
	
	public String getImgAURL() {
		return imgAURL;
	}

	public void setImgAURL(String imgAURL) {
		this.imgAURL = imgAURL;
	}
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	
	
}
