package uo.ri.ui.administrator.courses.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.Factory;

public class AddCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		CourseDto c = new CourseDto();
		user.fill( c );

		// Invoke the service
		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.registerNew(c);

		// Show result
		Console.println("New course registered: " + c.id);
	}

}
