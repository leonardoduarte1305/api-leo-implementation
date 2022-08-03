package dev.leoduarte.api.leo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.api.leo.app.persistence.DepartmentRepository;
import dev.leoduarte.api.leo.app.persistence.entity.Department;
import dev.leoduarte.model.DepartmentCreation;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public Department createDepartment(DepartmentCreation departmentCreation) {
		return departmentRepository.save(objectMapper.convertValue(departmentCreation, Department.class));
	}

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
}
