package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.AddMechanic;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {



	@Override
	public void execute() throws BusinessException {
		MechanicDto mechanicDto = new MechanicDto();
		// Get info
		mechanicDto.dni = Console.readString("Dni"); 
		mechanicDto.name = Console.readString("Name"); 
		mechanicDto.surname = Console.readString("Surname");
		
		AddMechanic addMechanic = new AddMechanic(mechanicDto);
		addMechanic.execute();
		// Print result
		Console.println("Mechanic added");
	}

}
