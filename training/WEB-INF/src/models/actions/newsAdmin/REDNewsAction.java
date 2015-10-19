package models.actions.newsAdmin;

import models.actions.auth.UpdateAccountAction;

import org.apache.log4j.Logger;
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
		@Result(name = "success", type = "redirectAction", location = "list-news.html"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),
})
@ParentPackage("master_login")
public class REDNewsAction extends BaseAction implements ModelDriven<News> {
	
	protected static Logger log = Logger.getLogger(UpdateAccountAction.class);
	private static final long serialVersionUID = 1L;
	private News news = new News();
	private int newsId;
	
	// ======================================================================================================
	@Action("/remove-news")
	public String removeNewsAction() {
		try {
			// REMOVE NEWS
			NewsMapper newsMapper = new NewsDAO();
			int cons = newsMapper.deleteNews(getNewsId());
			if (cons == 0) {
				showError("Error while remove news. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while delete news. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Action("/enable-news")
	public String enableNewsAction() {
		try {
			// ENABLE NEWS
			NewsMapper newsMapper = new NewsDAO();
			int cons = newsMapper.enableNews(getNewsId());
			if (cons == 0) {
				showError("Error while enable news. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while active news. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Action("/disable-news")
	public String disableNewsAction() {
		try {
			// ENABLE NEWS
			NewsMapper newsMapper = new NewsDAO();
			int cons = newsMapper.disableNews(getNewsId());
			if (cons == 0) {
				showError("Error while disable news. Try again");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while disactive news. Try again");
			return ERROR;
		}
	}
	
	// ======================================================================================================
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	
	// ======================================================================================================
	// GETTER _ SETTER
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
}
