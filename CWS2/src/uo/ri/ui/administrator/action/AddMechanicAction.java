package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.MechanicCrudServiceImpl;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {



	@Override
	public void execute() throws BusinessException {
		MechanicDto mechanicDto = new MechanicDto();
		// Get info
		mechanicDto.dni = Console.readString("Dni"); 
		mechanicDto.name = Console.readString("Name"); 
		mechanicDto.surname = Console.readString("Surname");
		
		MechanicCrudService mcs = new MechanicCrudServiceImpl();
		mcs.addMechanic(mechanicDto);
		
		// Print result
		Console.println("Mechanic added");
	}

}
