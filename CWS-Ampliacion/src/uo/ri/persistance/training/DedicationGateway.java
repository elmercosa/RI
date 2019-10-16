package uo.ri.persistance.training;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.ampliacion.DedicationDto;

public interface DedicationGateway {
	
	void setConnection(Connection c);
	
	List<DedicationDto> findDedicationByCourse(Long course_id);
}