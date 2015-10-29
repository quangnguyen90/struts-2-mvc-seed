package dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import dao.domain.News;

public interface NewsMapper {	
  public News select(News mastNews);
  public int insertNews(News news);
  public int updateNews(News news);
  public News getNewsById(Integer newsId);
  public int deleteNews(Integer newsId);
  public int enableNews(Integer newsId);
  public int disableNews(Integer newsId);
  
  public ArrayList<News>getAllNews(@Param("limit") int limit, @Param("offset") int offset) throws Exception;	
  public ArrayList<News>getAllNewsOn(@Param("limit") int limit, @Param("offset") int offset) throws Exception;	
  public ArrayList<News>get8NewsOn();
  public News getTheLastestNews();
  public int countForSearch() throws Exception;
  public int countForSearchNewsOn() throws Exception;
}