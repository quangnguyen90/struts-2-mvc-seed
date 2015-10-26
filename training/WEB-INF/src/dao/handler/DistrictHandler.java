package dao.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.base.DbConnection;

import dao.domain.District;

public class DistrictHandler {
	private Connection connection;
	
	public DistrictHandler(){
		try {
			connection = DbConnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// SELECT DISTRICT LIST
	public List<District> selectDistrictFromCity(int city_id) throws Exception
	{
		List<District> districtList = new ArrayList<District>();
		// QUERY STRING
		String query = "SELECT districtId, districtName, cityId, status FROM district WHERE cityId = "+city_id ;
		
		try {
			Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
			while (rs.next() != false) {
				// result = rs.getString( 1 );
				District district = new District();
				district.setDistrictId(rs.getInt("districtId"));
				district.setCityId(rs.getInt("cityId"));
				district.setDistrictName(rs.getString("districtName"));
				district.setStatus(rs.getBoolean("status"));
				districtList.add(district);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return districtList;
	}
}
