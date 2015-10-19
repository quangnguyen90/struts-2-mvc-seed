package models.actions.newsAdmin;

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
		@Result(name = "success", type = "tiles", location = "tiles.list_news"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),

})
@ParentPackage("master_login")
public class ListNewsAction extends BaseAction implements ModelDriven<News> {
	private static final long serialVersionUID = 1L;
	private List<News> newsResults;
	private ArrayList<String> lstPage;
	private String contextPath = null;
	private NewsMapper newsMapper = new NewsDAO();
	private News news = new News();
	private static final String COUNT_STRING = "count_string";
	// ======================================================================================================
	@Action("/list-news")
	public String listNews() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		contextPath = request.getContextPath();
		try {
			if (request.getParameter("numberPage") != null) 
			{
				this.setOffset(Integer.parseInt(request.getParameter("numberPage")));
			}
			this.setCount(newsMapper.countForSearch());
			newsResults = newsMapper.getAllNews(this.getItemPerPage(),((this.getOffset() - 1) * this.getItemPerPage()));
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
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	//===============================================================================================
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	//===============================================================================================
	// GETTER _ SETTER
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
}
