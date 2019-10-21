package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class AssignMechanicToWorkorder {
	private Long workorderID;
	private Long mechanicID;

	public AssignMechanicToWorkorder(Long workorderID, Long mechanicID) {
		super();
		this.workorderID = workorderID;
		this.mechanicID = mechanicID;
	}

	/*
	 * 
	 * - the mechanic does not exist, or - the work order does not exist, or - the
	 * work order is not in OPEN status
	 */
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway gateway = Factory.persistance.getWorkOrderGateway();
			gateway.setConnection(c);
			gateway.assignMechanic(workorderID, mechanicID);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("ERROR");
		}

	}

}
