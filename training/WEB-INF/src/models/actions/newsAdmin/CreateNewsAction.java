package models.actions.newsAdmin;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import models.actions.auth.RegisterAccountAction;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;
import common.base.BaseAction;
import common.util.MiscUtils;
import common.util.ValidateUtil;

import dao.dao.NewsDAO;
import dao.domain.News;
import dao.mapper.NewsMapper;

@Results({
		@Result(name = "createNews", type = "tiles", location = "tiles.create_news"),
		@Result(name = "successex", type = "redirectAction", location = "create-news.html"),
		@Result(name = "success", type = "tiles", location = "tiles.create_news"),
		@Result(name = "error", type = "tiles", location = "tiles.error"),

})
@ParentPackage("master_login")
public class CreateNewsAction extends BaseAction implements ModelDriven<News> {
	protected static Logger log = Logger.getLogger(RegisterAccountAction.class);
	private static final long serialVersionUID = 1L;
	// For Save images
	private File myNewsAvatar;
	private String myNewsAvatarFileName, myNewsAvatarContentType;
	private News news = new News();
	// ======================================================================================================
	@Action("/create-news")
	public String createNews() {
		return "createNews";
	}
	
	@Action("/create-news-action")
	public String createNewsAction() {
		try {
			String type = ServletActionContext.getRequest().getMethod();
			
			if(type.toUpperCase() == "GET")
			{
				return "successex";
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
				news.setCreated(dateFormat.parse(dateFormat.format(date)));
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
				news.setAvatar("/assets/img/news-img/awsome.jpg");
			}
			// ========================================================================================
			// INSERT NEWS
			NewsMapper newsMapper = new NewsDAO();
			int newsResult = newsMapper.insertNews(news);
			if (newsResult == 0) {
				showError("Error while creatting news. Try again");
				return ERROR;
			}
			
			setSuccessMessage("Create News OK");
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			showError("Error while creat news. Try again");
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
			String titleAvatar = MiscUtils.returnRandomPassword(5);
			news.setAvatar(path + "/" + titleAvatar + ValidateUtil.fileExtention(myNewsAvatarContentType));
			FileUtils.copyFile(this.myNewsAvatar, new File(filePath, titleAvatar + ValidateUtil.fileExtention(myNewsAvatarContentType)));
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
		
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
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
}
