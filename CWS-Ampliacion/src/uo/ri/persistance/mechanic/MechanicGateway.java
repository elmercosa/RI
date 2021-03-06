package uo.ri.persistance.mechanic;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * Add a new mechanic to the system with the data specified in the dto. The id
	 * value will be ignored
	 * 
	 * @param mechanic dto
	 */
	void add(MechanicDto mechanic);

	/**
	 * Remove a mechanic to the system for an id.
	 * 
	 * @param mechanic_id
	 */
	void delete(Long mechanic_id);

	/**
	 * Update a mechanic to the system with the data specified in the dto.
	 * 
	 * @param mechanic dto
	 */
	void update(MechanicDto mechanic);

	/**
	 * @return the list of all mechanics registered in the system. It might be an
	 *         empty list if there is no mechanic
	 * 
	 */
	List<MechanicDto> findAll();

	/**
	 * @param id of the mechanic
	 * @return the dto for the mechanic or null if there is none with the id
	 */
	MechanicDto findByID(Long idMechanic);

	/**
	 * @param dni of the mechanic
	 * @return the dto for the mechanic or null if there is none with the dni
	 */
	MechanicDto findByDNI(String dni);
}
