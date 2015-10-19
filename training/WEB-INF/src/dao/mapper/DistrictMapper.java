package dao.mapper;

import java.util.ArrayList;

import dao.domain.District;

public interface DistrictMapper {

	public ArrayList<District> selectDistrictFromCity(int cityID);
}
