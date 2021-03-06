package uo.ri.ui.administrator.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMechanic = Console.readLong("Type mechanic id ");
		MechanicCrudService mcs = Factory.service.getMechanicCrudService();
		mcs.deleteMechanic(idMechanic);
		Console.println("Mechanic deleted");
	}

}
