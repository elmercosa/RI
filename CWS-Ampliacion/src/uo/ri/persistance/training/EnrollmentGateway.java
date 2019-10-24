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
	
	/**
	 * Find number of mechanics registred into a course
	 * @param id
	 * @return
	 */
	int findRegistrationByCourse(Long id);

	/**
	 * @return the list of enrollments registered in the system for an specific
	 *         mechanic. It might be an empty list if there is no enrollment
	 * 
	 */
	List<EnrollmentDto> findEnrollmentByCourseID(Long id);
	
	/**
	 * 
	 * @param courseID
	 * @param mechanicID
	 * @return return a enrollment dto for a courseID and mechanicID 
	 */
	EnrollmentDto findEnrollment(Long courseID, Long mechanicID);
	
	/**
	 * Insert a new enrollment into the database
	 * @param dto
	 */
	void add(EnrollmentDto dto);

	/**
	 * 
	 * @return the last id generated
	 */
	Long findLastID(); 
	
	/**
	 * Delete a enrollment by id
	 * @param enrollment id
	 */
	void delete(Long id);
	
	/**
	 * Find enrollment by id
	 * @param enrollment id
	 * @return enrollment dto if id exist: null if not
	 */
	EnrollmentDto findEnrollmentByID(Long id);
}
