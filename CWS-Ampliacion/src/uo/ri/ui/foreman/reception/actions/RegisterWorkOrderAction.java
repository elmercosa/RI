package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.ampliacion.VehicleDto;
import uo.ri.business.dto.ampliacion.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class RegisterWorkOrderAction implements Action {

	private WorkOrderUserInteractor user = new WorkOrderUserInteractor();

	@Override
	public void execute() throws BusinessException {

		VehicleDto v = user.askForVehicle();
		Printer.printVehicleDetail( v );

		WorkOrderDto wo = user.askForWorkOrder(v);

		WorkOrderService as = Factory.service.forWorkOrderService();
		as.registerNew( wo );

		Console.println("\nWork order registered: " + wo.id);
	}

}