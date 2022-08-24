package dev.leoduarte.api.leo.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.api.leo.app.exceptions.EmployeeNotFoundException;
import dev.leoduarte.api.leo.app.persistence.EmployeeRepository;
import dev.leoduarte.api.leo.app.persistence.entity.Department;
import dev.leoduarte.api.leo.app.persistence.entity.Employee;
import dev.leoduarte.api.leo.app.persistence.entity.Profile;
import dev.leoduarte.model.EmployeeCreation;
import dev.leoduarte.model.EmployeeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentService departmentService;
	private final ObjectMapper objectMapper;

	public Employee createEmployee(EmployeeCreation employeeCreation) {
		Long id = employeeCreation.getDepartment().longValue();
		Department department = departmentService.getById(id);

		Employee employee = objectMapper.convertValue(employeeCreation, Employee.class);
		employee.setDepartment(department);

		return employeeRepository.saveAndFlush(employee);
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

	public Employee updateEmployee(long id, EmployeeProperties employeeProperties) {
		Employee employee = getEmployee(id);
		Department department = departmentService.getById(employeeProperties.getDepartment().longValue());

		employee.setDepartment(department);
		employee.setName(employeeProperties.getName());
		employee.setProfile(objectMapper.convertValue(employeeProperties.getProfile(), Profile.class));

		return employeeRepository.save(employee);
	}

	private Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(
				String.format("Employee with id: %d does not exist.", id)));
	}
}
