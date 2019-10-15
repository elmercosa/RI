package uo.ri.persistance;

import java.sql.Connection;

import uo.ri.business.dto.ampliacion.CourseDto;

public interface CourseGateway {
	void setConnection(Connection c);

	CourseDto findCourseByID(Long courseID);
}
