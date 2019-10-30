package uo.ri.business.serviceLayer.training.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.training.CourseAttendanceService;
import uo.ri.business.transactionScripts.administrator.course.FindAllCourses;
import uo.ri.business.transactionScripts.administrator.courseAttendance.FindAllEnrollmentsByCourse;
import uo.ri.business.transactionScripts.administrator.courseAttendance.RegisterNewAttendance;
import uo.ri.business.transactionScripts.administrator.mechanic.ListMechanics;
import uo.ri.common.BusinessException;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
		RegisterNewAttendance a = new RegisterNewAttendance(dto);
		return a.execute();
	}

	@Override
	public void deleteAttendace(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long id) throws BusinessException {
		FindAllEnrollmentsByCourse d = new FindAllEnrollmentsByCourse(id);
		return d.execute();
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		FindAllCourses a = new FindAllCourses();
		return a.execute();
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		ListMechanics a = new ListMechanics();
		return a.execute();
	}

}