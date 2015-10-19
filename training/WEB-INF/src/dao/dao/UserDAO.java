package dao.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import common.base.DaoConfig;
import dao.domain.User;
import dao.mapper.NewsMapper;
import dao.mapper.UserMapper;

public class UserDAO implements UserMapper {

	SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
	Logger log = Logger.getLogger(UserDAO.class);

	@Override
	public User select(User mastUser) {
		try {
			return session.getMapper(UserMapper.class).select(mastUser);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public int insertUser(User mastUser) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).insertUser(mastUser);
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
	public int updatePassword(User mastUser) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).updatePassword(mastUser);
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
	public User validateUserExist(String username) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateUserExist(username);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User validateUserEmailExist(String email) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateUserEmailExist(email);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User validateUserStatus(String username) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateUserStatus(username);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User getUserById(String username) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).getUserById(username);
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User validateUserOrNot(String username) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateUserOrNot(username);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User getUserById2(String username) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).getUserById2(username);
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User validateOldPassword(User mastUser) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateOldPassword(mastUser);
		} catch (Exception e) {
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public User validateActiveUser(String username) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateActiveUser(username);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int updateUser(User mastUser) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).updateUser(mastUser);
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
	public User retrievePassword(User mastUser) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).retrievePassword(mastUser);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally{
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public int updateTemporaryPassword(User mastUser) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).updateTemporaryPassword(mastUser);
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
	public User validateTemporaryPassword(String temporaryPassword) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).validateTemporaryPassword(temporaryPassword);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public User validateEmailMatchUsername(User mastUser) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).validateEmailMatchUsername(mastUser);
		} catch (Exception e) {
			log.error(e.getMessage());
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public int deleteUser(String username) {
		// TODO Auto-generated method stub
		try {
			return session.getMapper(UserMapper.class).deleteUser(username);
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
	public User login(User mastUser) {
		try {
			return session.getMapper(UserMapper.class).select(mastUser);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return null;
	}

	@Override
	public int enableUser(String username) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).enableUser(username);
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
	public int disableUser(String username) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).disableUser(username);
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
	public int resetUserPassword(String username) {
		// TODO Auto-generated method stub
		SqlSession session = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).resetUserPassword(username);
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
	public ArrayList<User> getAllUsers(@Param("limit") int limit,
			@Param("offset") int offset) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
		try {
			return session.getMapper(UserMapper.class).getAllUsers(limit, offset);
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
			return session.getMapper(UserMapper.class).countForSearch();
		}catch (Exception e) {
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

}
