package dao.dao;

import org.apache.ibatis.session.SqlSession;

import common.base.DaoConfig;

import dao.domain.User;
import dao.mapper.UserMapper;

public class UserDAO  implements UserMapper {

	SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();

		@Override
	public User select(User mastUser) {
		try{
			return session.getMapper(UserMapper.class).select(mastUser);
		}catch (Exception e) {
			session.rollback();
		}finally{
			session.close();
		}
		return null;

	}
	
}
