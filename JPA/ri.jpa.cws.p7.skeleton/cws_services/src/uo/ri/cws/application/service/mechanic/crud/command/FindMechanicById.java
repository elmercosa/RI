package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

public class FindMechanicById implements Command< Optional<MechanicDto>> {

	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
	private String id;

	public FindMechanicById(String id) {
		this.id = id;
	}

	@Override
	public Optional<MechanicDto> execute() throws BusinessException {
		return Optional.of(DtoAssembler.toDto(mechanicRepository.findById(id).get()));
	}

}