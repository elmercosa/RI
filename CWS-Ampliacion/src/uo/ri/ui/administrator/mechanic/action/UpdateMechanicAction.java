package uo.ri.ui.administrator.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		MechanicDto mechanicDto = new MechanicDto();
		// Get info
		mechanicDto.id = Console.readLong("Type mechahic id to update");
		mechanicDto.dni = Console.readString("Dni");
		mechanicDto.name = Console.readString("Name");
		mechanicDto.surname = Console.readString("Surname");

		MechanicCrudService mcs = Factory.service.getMechanicCrudService();
		mcs.updateMechanic(mechanicDto);

		// Print result
		Console.println("Mechanic updated");
	}

}
