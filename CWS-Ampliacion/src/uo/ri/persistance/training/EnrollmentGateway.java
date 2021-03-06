package uo.ri.persistance.training;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.EnrollmentDto;

public interface EnrollmentGateway {
	
	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);
	
	/**
	 * @return the list of enrollments registered in the system for an specific
	 *         mechanic. It might be an empty list if there is no enrollment
	 * 
	 */
	List<EnrollmentDto> findPassedEnrollmentsByMechanicId(Long mechanic_id);
	
	/**
	 * @return the list of passed courses id registered in the system for an specific
	 *         mechanic id. It might be an empty list if there is no enrollment
	 * 
	 */
	List<Long> findPassedCoursessByMechanicId(Long mechanic_id);
	
	/**
	 * @return the list of mechanic id registered in the system. It might be an empty list if there is no mechanic
	 * 
	 */
	List<Long> findPassedMechanicIDS();
	
	/**
	 * Find the percentage of attendance for a mechanic in a course
	 * @param courseID
	 * @param mechanicId
	 * @return attendance percentage
	 */
	int findAttendanceForCourseAndMechanic(Long courseID, Long mechanicId);

	
	/**
	 * 
	 * @param mechanic_id
	 * @return the list of all courses id registered in the system for an specific
	 *         mechanic id. It might be an empty list if there is no enrollment
	 */
	List<Long> findCoursesByMechanicId(Long mechanic_id);
}
