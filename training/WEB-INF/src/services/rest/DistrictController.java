package services.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.handler.DistrictHandler;
import dao.domain.District;

@Path("/district")
public class DistrictController{

	private ArrayList<District> district;
	private int city_ID;
	
	@GET
	@Path("/getDistrictByCityAction/{city_id}")
	@Produces("application/json")
	public ArrayList<District> execute(@PathParam(value = "city_id") int city_id) {
		DistrictHandler handler = new DistrictHandler();       
		ArrayList<District> district = new ArrayList<District>();
		try {
			district=handler.selectDistrictFromCity(city_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return district;
	}
	
	public int getCity_ID() {
		return city_ID;
	}

	public void setCity_ID(int city_ID) {
		this.city_ID = city_ID;
	}

	public ArrayList<District> getDistrict() {
		return district;
	}

	public void setDistrict(ArrayList<District> district) {
		this.district = district;
	}
	
}
