package dev.leoduarte.api.leo.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dev.leoduarte.model.Employee;
import dev.leoduarte.model.EmployeeCreation;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

	@Mapping(target = "departmentId", source = "department.department_id")
	@Mapping(target = "profileId", source = "profile.profile_id")
	@Mapping(target = "employeeId", source = "employee_id")
	Employee toEmployeeDto(dev.leoduarte.api.leo.app.persistence.entity.Employee employeeEntity);


	dev.leoduarte.api.leo.app.persistence.entity.Employee toEmployeeEntity(EmployeeCreation employeeCreation);
}
