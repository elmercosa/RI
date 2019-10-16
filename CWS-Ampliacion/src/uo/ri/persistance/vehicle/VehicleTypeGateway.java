package uo.ri.persistance.vehicle;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.ampliacion.VehicleTypeDto;

public interface VehicleTypeGateway {

	void setConnection(Connection c);
	
	List<VehicleTypeDto> findAll();
}
