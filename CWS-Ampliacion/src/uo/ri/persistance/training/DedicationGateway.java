package uo.ri.persistance.training;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.DedicationDto;

public interface DedicationGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * @return the list of dedications registered in the system for an specific
	 *         course. It might be an empty list if there is no mechanic
	 * 
	 */
	List<DedicationDto> findDedicationByCourse(Long course_id);

	/**
	 * @return the list of vehicle types registered in the dedications for an specific
	 *         course. It might be an empty list if there is no mechanic
	 * 
	 */
	List<Long> findCoursesByVehicleType(Long vehicletype_id);

	/**
	 * Find the percentage of dedication to a course
	 * @param courseID
	 * @param vehicletypeID
	 * @return % dedicated
	 */
	int findPercentageForCourse(Long courseID, Long vehicletypeID);
}
