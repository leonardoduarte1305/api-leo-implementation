package dev.leoduarte.api.leo.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.api.leo.app.exceptions.DepartmentNotFoundException;
import dev.leoduarte.api.leo.app.persistence.DepartmentRepository;
import dev.leoduarte.api.leo.app.persistence.entity.Department;
import dev.leoduarte.model.DepartmentCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ObjectMapper objectMapper;

	public Department createDepartment(DepartmentCreation departmentCreation) {
		return departmentRepository.saveAndFlush(
				objectMapper.convertValue(
						departmentCreation,
						Department.class));
	}

	public void deleteDepartment(Long id) {
		departmentRepository.delete(getDepartment(id));
	}

	public Department getById(Long id) {
		return getDepartment(id);
	}

	public List<dev.leoduarte.model.Department> getAll() {
		return departmentRepository.findAll().stream().map(d ->
						objectMapper.convertValue(d, dev.leoduarte.model.Department.class))
				.collect(Collectors.toList());
	}

	public Department updateDepartment(Long id, DepartmentCreation departmentCreation) {
		Department department = getDepartment(id);
		department.setDescription(departmentCreation.getDescription());

		return departmentRepository.saveAndFlush(department);
	}

	private Department getDepartment(Long id) {
		return departmentRepository.findById(id).orElseThrow(
				() -> new DepartmentNotFoundException(
						String.format("Department with id: %d does not exist.", id)));
	}

}
