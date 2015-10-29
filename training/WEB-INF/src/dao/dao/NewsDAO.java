package dao.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import common.base.DaoConfig;
import dao.domain.News;
import dao.mapper.NewsMapper;

public class NewsDAO implements NewsMapper {

	SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
	Logger log = Logger.getLogger(UserDAO.class);


	@Override
	public News select(News mastNews) {
		try {
			return session.getMapper(NewsMapper.class).select(mastNews);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public News getNewsById(Integer newsId) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).getNewsById(newsId);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int insertNews(News news) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).insertNews(news);
		} catch (Exception e) {
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return 0;
	}

	@Override
	public int updateNews(News news) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).updateNews(news);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return 0;
	}

	@Override
	public int deleteNews(Integer newsId) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).deleteNews(newsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return 0;
	}

	@Override
	public int enableNews(Integer newsId) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).enableNews(newsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return 0;
	}

	@Override
	public int disableNews(Integer newsId) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(NewsMapper.class).disableNews(newsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return 0;
	}

	@Override
	public ArrayList<News> getAllNews(@Param("limit") int limit,
			@Param("offset") int offset) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(NewsMapper.class).getAllNews(limit, offset);
		}catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public int countForSearch() throws Exception {
		SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(NewsMapper.class).countForSearch();
		}catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public int countForSearchNewsOn() throws Exception {
		SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(NewsMapper.class).countForSearchNewsOn();
		}catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public ArrayList<News> getAllNewsOn(@Param("limit") int limit,
			@Param("offset") int offset) throws Exception {
		SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(NewsMapper.class).getAllNews(limit, offset);
		}catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public ArrayList<News> get8NewsOn() {
		try {
			return session.getMapper(NewsMapper.class).get8NewsOn();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public News getTheLastestNews() {
		try {
			return session.getMapper(NewsMapper.class).getTheLastestNews();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}
}
