package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.training.EnrollmentGateway;

public class DeleteEnrollment {
	
	private Long id;
	
	public DeleteEnrollment(Long id) {
		this.id = id;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			if(gateway.findEnrollmentByID(id) == null) {
				c.rollback();
				throw new BusinessException("The enrollment does not exist");
			}
			gateway.delete(id);
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
}
