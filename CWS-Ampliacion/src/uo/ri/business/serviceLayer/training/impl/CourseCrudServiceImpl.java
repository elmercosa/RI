package uo.ri.business.serviceLayer.training.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.business.transactionScripts.administrator.FindAllCourses;
import uo.ri.business.transactionScripts.administrator.ListVehicleTypes;
import uo.ri.common.BusinessException;

public class CourseCrudServiceImpl implements CourseCrudService {

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		return null;
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		FindAllCourses fa = new FindAllCourses();
		return fa.execute();
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		ListVehicleTypes lv = new ListVehicleTypes();
		return lv.execute();
	}

	@Override
	public CourseDto findCourseById(Long cId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
