package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.persistance.mechanic.MechanicGateway;

public class DeleteMechanic {
	private long id;

	public DeleteMechanic(long id) {
		super();
		this.id = id;
	}

	public void execute() throws BusinessException  {
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
}
