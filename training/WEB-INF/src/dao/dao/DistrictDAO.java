package dao.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import common.base.DaoConfig;

import dao.domain.District;
import dao.mapper.DistrictMapper;

public class DistrictDAO implements DistrictMapper {

	SqlSession session  = DaoConfig.getSqlSessionFactory().openSession();
	Logger log = Logger.getLogger(CityDAO.class);
	// =====================================================================================================
	// FILTER DISTRICT LIST FROM CITY ID
	@Override
	public ArrayList<District> selectDistrictFromCity(int cityID) {
		// TODO Auto-generated method stub
		try{
			return session.getMapper(DistrictMapper.class).selectDistrictFromCity(cityID);
		}catch (Exception e) {
			session.rollback();
		}finally{
			session.close();
		}
		
		return null;
	}
}
