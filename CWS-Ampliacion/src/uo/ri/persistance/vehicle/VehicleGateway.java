package uo.ri.persistance.vehicle;

import java.sql.Connection;

import uo.ri.business.dto.VehicleDto;

public interface VehicleGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * @param id of the vehicle
	 * @return the dto for the vehicle or null if there is none with the plate
	 */
	VehicleDto findVehicleByPlate(String plate);

	
	/**
	 * @param id of the vehicle
	 * @return the dto for the vehicle or null if there is none with the id
	 */
	VehicleDto findVehicleByID(Long id);

}
