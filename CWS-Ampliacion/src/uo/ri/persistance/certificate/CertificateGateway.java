package uo.ri.persistance.certificate;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CertificateDto;

public interface CertificateGateway {

	/**
	 * Set the database connection
	 * 
	 * @param database connection
	 */
	void setConnection(Connection c);

	/**
	 * @return the list of all certificates registered in the system. It might be an
	 *         empty list if there is no certifications
	 * 
	 */
	List<CertificateDto> findAll();

	/**
	 * 
	 * @param id of the mechanic
	 * @param id of the vehicle type
	 * @return the dto for the certificate or null if there is none with the ids
	 */
	CertificateDto findCertificateByMechanic(Long mechanicId, Long vehicletype_Id);
	
	
	/**
	 * Add a new certificate to the system with the data specified in the params. The id value will be ignored
	 * @param id of the mechanic
	 * @param id of the vehicle type
	 */
	void generateCertificate(Long mechanicID, Long vehicleTypeID);
	
	List<CertificateDto> findCertificatesByVehicleID(Long vehicleID);
}
