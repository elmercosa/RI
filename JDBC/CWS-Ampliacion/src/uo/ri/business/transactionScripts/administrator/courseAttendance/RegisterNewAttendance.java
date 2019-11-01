package uo.ri.business.transactionScripts.administrator.courseAttendance;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.EnrollmentGateway;

public class RegisterNewAttendance {
	private EnrollmentDto dto;
	
	public RegisterNewAttendance(EnrollmentDto dto) {
		this.dto = dto;
	}
	
	public EnrollmentDto execute() throws BusinessException {
		checkMechanic(dto.mechanicId);
		checkCourse(dto.courseId);
		checkEnrollment(dto.courseId,dto.mechanicId);
		checkAttendance();
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			gateway.add(dto);
			c.commit();
			dto.id = gateway.findLastID();
			return dto;
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	
	private void checkAttendance() throws BusinessException {
		if(dto.attendance < 85 && dto.passed) {
			throw new BusinessException("The attendance is under 85% and the course is marked as passed");
		}
		if(dto.attendance < 0 || dto.attendance > 100) {
			throw new BusinessException("The value for percentage is not in the range 0..100");
		}
		
	}
	private void checkEnrollment(String courseId , String mechanicId) throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			if(gateway.findEnrollment(Long.parseLong(courseId), Long.parseLong(mechanicId)) != null) {
				c.rollback();
				throw new BusinessException("There already is another enrollment registered for the same mechanic and course");
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
		
	}
	private void checkMechanic(String mechanicId) throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			MechanicGateway gateway = Factory.persistance.getMechanicCrudService();
			gateway.setConnection(c);
			if(gateway.findByID(Long.parseLong(mechanicId)) == null) {
				c.rollback();
				throw new BusinessException("The mechanic id does not exit");
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	private void checkCourse(String courseId) throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			CourseGateway gateway = Factory.persistance.getCourseGateway();
			gateway.setConnection(c);
			if(gateway.findCourseByID(Long.parseLong(courseId)) == null){
				c.rollback();
				throw new BusinessException("The course id does not exit");
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}