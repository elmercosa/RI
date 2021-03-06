package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.certificate.CertificateGateway;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;

public class GenerateCertificates {

	public int execute() throws BusinessException {
		int certificatesGenerated = 0;
		List<VehicleTypeDto> vehicleTypeDtos = findAllVehicleTypes();
		List<Long> mechanicIDs = findPassedMechanics();
		for (VehicleTypeDto vehicleTypeDto : vehicleTypeDtos) {
			for (Long mechanicID : mechanicIDs) {
				if (findCertificateByMechanic(mechanicID, vehicleTypeDto.id)) {
					int totalHours = 0;
					List<Long> coursesIDs = findCoursesForMechanicAndVehicleType(mechanicID, vehicleTypeDto.id);
					for (Long courseID : coursesIDs) {
						int hours = getCourseDuration(courseID);
						int pertentage = getPercentageForCourseVehicleType(courseID, vehicleTypeDto.id);
						int attendance = getAttendanceForMechanicInCourse(courseID, mechanicID);
						totalHours += (hours * pertentage / 100) * attendance / 100;
					}
					if (totalHours >= vehicleTypeDto.minTrainigHours) {
						generateCretificate(mechanicID, vehicleTypeDto.id);
						certificatesGenerated++;
					}
				}
			}
		}
		return certificatesGenerated;
	}

	public void generateCretificate(Long mechanicID, Long vehicleTypeID) throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			MechanicGateway gateMechanicGateway = Factory.persistance.getMechanicCrudService();
			VehicleTypeGateway gateVehicleTypeGateway = Factory.persistance.getVehicleTypeGateway();
			gateVehicleTypeGateway.setConnection(c);
			gateMechanicGateway.setConnection(c);
			gateway.setConnection(c);
			if(gateMechanicGateway.findByID(mechanicID) == null) {
				c.rollback();
				throw new BusinessException("Mechanic does not exist");
			}
			gateway.generateCertificate(mechanicID, vehicleTypeID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public int getAttendanceForMechanicInCourse(Long courseID, Long mechanicID) {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findAttendanceForCourseAndMechanic(courseID, mechanicID);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public int getPercentageForCourseVehicleType(Long courseID, Long vehicleTypeId) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			return gateway.findPercentageForCourse(courseID, vehicleTypeId);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public int getCourseDuration(Long courseID) {
		try (Connection c = Jdbc.getConnection()) {
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			gateway.setConnection(c);
			return gateway.findCourseByID(courseID).hours;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findCoursesForMechanicAndVehicleType(Long mechanicID, Long vehicleTypeID) {
		List<Long> coursesIDForMechanic = findPassedCoursessByMechanicId(mechanicID);

		List<Long> coursesIDForVehicleType = findCoursesByVehicleType(vehicleTypeID);

		List<Long> courses = new ArrayList<Long>();

		for (Long courseIds : coursesIDForVehicleType) {
			if (coursesIDForMechanic.contains(courseIds)) {
				courses.add(courseIds);
			}
		}
		return courses;

	}

	public List<VehicleTypeDto> findAllVehicleTypes() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway gateway = Factory.persistance.getVehicleTypeGateway();
			gateway.setConnection(c);
			return gateway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findPassedMechanics() {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findPassedMechanicIDS();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public boolean findCertificateByMechanic(Long mechLong, Long vehicletypeId) {
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway gateway = Factory.persistance.getCertificateGateway();
			gateway.setConnection(c);
			return gateway.findCertificateByMechanic(mechLong, vehicletypeId) == null;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findPassedCoursessByMechanicId(Long mechanic_id) {
		try (Connection c = Jdbc.getConnection()) {
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			return gateway.findPassedCoursessByMechanicId(mechanic_id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	public List<Long> findCoursesByVehicleType(Long vehicleType) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway gateway = Factory.persistance.getDedicationGateway();
			gateway.setConnection(c);
			return gateway.findCoursesByVehicleType(vehicleType);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
