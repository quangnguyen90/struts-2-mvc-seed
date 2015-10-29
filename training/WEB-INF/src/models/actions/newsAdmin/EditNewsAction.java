package models.actions.newsAdmin;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import models.actions.auth.UpdateAccountAction;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.ValidateUtil;

import dao.dao.NewsDAO;
import dao.domain.News;
import dao.mapper.NewsMapper;

@Results({
		@Result(name = "success", type = "tiles", location = "tiles.edit_news"),
		@Result(name = "error", type = "tiles", location = "tiles.edit_news"),
		@Result(name = "errorEx", type = "tiles", location = "tiles.error"),
})
@ParentPackage("master_login")
public class EditNewsAction extends BaseAction implements ModelDriven<News> {
	
	protected static Logger log = Logger.getLogger(UpdateAccountAction.class);
	private static final long serialVersionUID = 1L;
	private News news = new News();
	private int newsId, newsCode;
	private String contextPath = null;
	private String imgAURL;
	// For Save images
	private File myNewsAvatar;
	private String myNewsAvatarFileName, myNewsAvatarContentType;
	private String newsTitle, newsBrief, newsContent;
	// ======================================================================================================
	@Action("/edit-news")
	public String editNews() {
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
			
			// An other way to show news info
			newsCode = getNewsId();
			newsTitle = news.getTitle();
			newsBrief = news.getBrief();
			newsContent = news.getContent();
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while view detail news. Try again");
			return ERROR;
		}
	}
	
	@Action("/edit-news-action")
	public String editNewsAction() {
		try {
			news.setId(this.getNewsId());
			
			//LOAD NEWS INFO
			NewsMapper newsMapp = new NewsDAO();
			News newsResult = newsMapp.getNewsById(this.getNewsId());
			if(newsResult == null){
				showError("Error while getting news info. Try again");
				return ERROR;
			}
			
			// Check brief, title, content
			if (validateInputField() == false) {
				return ERROR;
			}
			// ==============================================================================================
			// PARSE STRING TO DATE
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				news.setUpdated(dateFormat.parse(dateFormat.format(date)));
			}
			catch(Exception ex) {
				showError("Error while parsing created/updated");
				return ERROR;
			}
			
			// ==============================================================================================
			// CHECK AVATAR
			if (getMyNewsAvatar() != null) {
				if(ValidateUtil.fileExtention(myNewsAvatarContentType)=="")
				 {
					showError("Image only allow: jpg, jpeg, png");
					return ERROR;
				}
				else
				{
					if(uploadFileToMyFolder()==false){
						showError("Error while upload file. Try again");
						return ERROR;
					}
				}
			} else {
				news.setAvatar(newsResult.getAvatar());
			}
			
			// UPDATE NEWS DETAIL
			NewsMapper newsMapper = new NewsDAO();
			int cons = newsMapper.updateNews(news);
			if (cons == 0) {
				showError("Error while update detail news. Try again");
				return ERROR;
			}
			
			//REFREH PAGE
			NewsMapper newsMap = new NewsDAO();
			News newsDB = newsMap.getNewsById(this.getNewsId());
			if(newsDB == null){
				showError("Error while getting news info. Try again");
				return ERROR;
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			imgAURL = newsDB.getAvatar();
			contextPath = request.getContextPath();
			
			setSuccessMessage("Update News OK");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			showError("Error while edit news. Try again");
			return ERROR;
		}
	}
	// ======================================================================================================
	// Upload Image
	public boolean uploadFileToMyFolder() {
		boolean check = true;
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = "/assets/img/news-img";
			// getting the path to where the images will be uploaded
			String filePath = servletContext.getRealPath(path);

			File uploadDir = new File(filePath);
			// if the folder does not exits, creating it
			if (uploadDir.exists() == false) {
				uploadDir.mkdirs();
			}
			news.setAvatar(path + "/" + news.getTitle() + ValidateUtil.fileExtention(myNewsAvatarContentType));
			FileUtils.copyFile(this.myNewsAvatar, new File(filePath, news.getTitle()+ ValidateUtil.fileExtention(myNewsAvatarContentType)));
			check = true;
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			addActionError(e.getMessage());
			check = false;
		}
		return check;
	}
	
	// ======================================================================================================
	// VALIDATE INPUT FIELD
	/**
	 * Validate input input form return message, true or false
	 */
	public boolean validateInputField() {
		boolean check = true;
		// =====================================================Validate EMPTY
		if (ValidateUtil.validateEmptyString(news.getTitle()) == true) {
			showError("Title is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(news.getBrief()) == true) {
			showError("Brief is required");
			check = false;
		}

		if (ValidateUtil.validateEmptyString(news.getContent()) == true) {
			showError("Content is required");
			check = false;
		}
		// =====================================================Validate LENGTH
		if (ValidateUtil.validateLengthOfTitle(news.getTitle()) == false) {
			showError("Title length: 2-250 characters");
			check = false;
		}
		return check;
	}
	// ======================================================================================================
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	
	// ======================================================================================================
	// GETTER _ SETTER
	public File getMyNewsAvatar() {
		return myNewsAvatar;
	}

	public void setMyNewsAvatar(File myNewsAvatar) {
		this.myNewsAvatar = myNewsAvatar;
	}

	public String getMyNewsAvatarFileName() {
		return myNewsAvatarFileName;
	}

	public void setMyNewsAvatarFileName(String myNewsAvatarFileName) {
		this.myNewsAvatarFileName = myNewsAvatarFileName;
	}

	public String getMyNewsAvatarContentType() {
		return myNewsAvatarContentType;
	}

	public void setMyNewsAvatarContentType(String myNewsAvatarContentType) {
		this.myNewsAvatarContentType = myNewsAvatarContentType;
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
		// Another way to get parameter on URL
		//newsId = Integer.parseInt(ServletActionContext.getRequest().getParameter("newsId"));
		this.newsId = newsId;
	}
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getImgAURL() {
		return imgAURL;
	}

	public void setImgAURL(String imgAURL) {
		this.imgAURL = imgAURL;
	}
	
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsBrief() {
		return newsBrief;
	}

	public void setNewsBrief(String newsBrief) {
		this.newsBrief = newsBrief;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public int getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(int newsCode) {
		this.newsCode = newsCode;
	}
}
