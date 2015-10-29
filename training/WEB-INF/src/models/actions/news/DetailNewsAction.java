package models.actions.news;

import javax.servlet.http.HttpServletRequest;

import models.actions.auth.UpdateAccountAction;

import org.apache.log4j.Logger;
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
	 @Result(name="success", type="tiles" ,location="tiles.detail_news"),
	 @Result(name="error" ,  type="tiles" ,location="tiles.error")
	})
@ParentPackage("master_tiles")
public class DetailNewsAction extends BaseAction implements ModelDriven<News>{
	
	protected static Logger log = Logger.getLogger(UpdateAccountAction.class);
	private static final long serialVersionUID = 1L;
	private int newsId, newsCode;
	private String imgAURL;
	private String contextPath = null;
	private NewsMapper newsMapper = new NewsDAO();
	private News news = new News();
	//=============================================================================================
	@Action("/news-detail")
	public String newsDetailAction() {
		try {
			// SHOW NEWS DETAIL
			NewsMapper newsMapper = new NewsDAO();
			news = newsMapper.getNewsById(getNewsId());
			if (news == null) {
				showError("Error while show detail news. Try again");
				return ERROR;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			imgAURL = news.getAvatar();
			contextPath = request.getContextPath();
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while view detail news. Try again");
			return ERROR;
		}
	}
	//=============================================================================================
	// GETTER _ SETTER
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
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getNewsCode() {
		return newsCode;
	}
	public void setNewsCode(int newsCode) {
		this.newsCode = newsCode;
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
