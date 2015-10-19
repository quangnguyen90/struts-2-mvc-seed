package dao.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import common.base.DaoConfig;

import dao.domain.City;
import dao.mapper.CityMapper;

public class CityDAO implements CityMapper {
	SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
	Logger log = Logger.getLogger(CityDAO.class);
	// =====================================================================================================
	// SHOW CITY LIST
	@Override
	public ArrayList<City> selectCityList() {
		// TODO Auto-generated method stub
		try{
			return session.getMapper(CityMapper.class).selectCityList();
		}catch (Exception e) {
			session.rollback();
		}finally{
			session.close();
		}
		return null;
	}
}
