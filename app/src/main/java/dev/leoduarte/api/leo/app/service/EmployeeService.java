package dev.leoduarte.api.leo.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.api.leo.app.exceptions.DepartmentNotFoundException;
import dev.leoduarte.api.leo.app.persistence.EmployeeRepository;
import dev.leoduarte.api.leo.app.persistence.entity.Employee;
import dev.leoduarte.model.EmployeeCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ObjectMapper objectMapper;

	public Employee createEmployee(EmployeeCreation employeeCreation) {
		return employeeRepository.saveAndFlush(
				objectMapper.convertValue(
						employeeCreation,
						Employee.class));
	}

	public void deleteEmployee(Long id) {
		employeeRepository.delete(getEmployee(id));
	}

	public List<dev.leoduarte.model.Employee> getAll() {
		return employeeRepository.findAll().stream().map(e ->
						objectMapper.convertValue(e, dev.leoduarte.model.Employee.class))
				.collect(Collectors.toList());
	}

	public Employee getById(Long id) {
		return getEmployee(id);
	}

	public Employee updateEmployee(long id, EmployeeCreation employeeCreation) {
		// TODO Implement the updateEmployee
		return null;
	}

	private Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(
				String.format("Employee with id: %d does not exist.", id)));
	}
}
