package dao.dao;

import dao.domain.MailConfig;
import dao.mapper.MailConfigMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import common.base.DaoConfig;

public class MailConfigDAO  implements MailConfigMapper {

	SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
	Logger log = Logger.getLogger(MailConfigDAO.class);
	
	

	@Override
	public MailConfig selectMailConfigByID(int ID){
		// TODO Auto-generated method stub
		try {
			return session.getMapper(MailConfigMapper.class).selectMailConfigByID(ID);
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}
}
