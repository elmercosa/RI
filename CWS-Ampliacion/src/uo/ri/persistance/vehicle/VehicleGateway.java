package uo.ri.persistance.vehicle;

import java.sql.Connection;

import uo.ri.business.dto.VehicleDto;

public interface VehicleGateway {
	
	void setConnection(Connection c);
	
	VehicleDto findVehicleByPlate(String plate);

}
