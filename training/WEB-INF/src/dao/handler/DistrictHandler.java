package dao.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.base.DbConnection;

import dao.domain.District;

public class DistrictHandler {
	public ArrayList<District> selectDistrictFromCity(int city_id) throws Exception
	{
		ArrayList<District> districtList = new ArrayList<District>();
		DbConnection database = new DbConnection();
		Connection connection = database.getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT districtId, districtName, cityId, status FROM district WHERE cityId = "+city_id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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
		}

		if (connection != null) {
			try {
				connection.close();
				// Log.info( "Database connection terminated" );
			} catch (Exception e) {
			}
		}
		
		return districtList;
	}
}
