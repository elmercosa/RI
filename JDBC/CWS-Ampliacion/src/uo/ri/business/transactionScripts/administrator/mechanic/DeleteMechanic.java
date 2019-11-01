package uo.ri.business.transactionScripts.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class DeleteMechanic {
	private long id;

	public DeleteMechanic(long id) {
		super();
		this.id = id;
	}

	public void execute() throws BusinessException  {
		checkEnrollment();
		checkWorkOrder();
		try (Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			MechanicGateway gateway = Factory.persistance.getMechanicCrudService();
			gateway.setConnection(c);
			if(gateway.findByID(id) == null) {
				c.rollback();
				throw new BusinessException("Mechanic does not exist");
			}
			gateway.delete(id);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private void checkWorkOrder() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			if(gateway.mechanicAssigned(id)) {
				c.rollback();
				throw new BusinessException("Mechanic is assigned for any workorder");
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}

	private void checkEnrollment() throws BusinessException {
		try (Connection c = Jdbc.getConnection()){
			EnrollmentGateway gateway = Factory.persistance.getEnrollmentGateway();
			gateway.setConnection(c);
			if(gateway.findCoursesByMechanicId(id) == null) {
				c.rollback();
				throw new BusinessException("Mechanic is enrolled");
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}
	}
	
	
}