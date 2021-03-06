package uo.ri.ui.administrator.mechanic.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListMechanicsByVehicleTypeAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics who have attended the training by type of vehicle\n");
		CourseReportService service = Factory.service.getCourseReportService();
		List<TrainingHoursRow> trainingHoursRows = service.findTrainingByVehicleTypeAndMechanic();
		for (TrainingHoursRow trainingHoursRow : trainingHoursRows) {
			Printer.printTrainingHoursRow(trainingHoursRow);
		}

	}
}
