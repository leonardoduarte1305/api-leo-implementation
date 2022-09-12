package dev.leoduarte.api.leo.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.api.leo.app.exceptions.EmployeeNotFoundException;
import dev.leoduarte.api.leo.app.exceptions.ProfileNotFoundException;
import dev.leoduarte.api.leo.app.persistence.EmployeeRepository;
import dev.leoduarte.api.leo.app.persistence.ProfileRepository;
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
	private final ProfileRepository profileRepository;
	private final DepartmentService departmentService;
	private final ObjectMapper objectMapper;

	public Employee createEmployee(EmployeeCreation employeeCreation) {
		Profile profile = getProfile(employeeCreation.getProfileId().longValue());

		Employee employee = objectMapper.convertValue(employeeCreation, Employee.class);
		employee.setProfile(profile);
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

		Long department_id = employeeProperties.getDepartmentId().longValue();

		Long profile_id = employeeProperties.getProfileId().longValue();
		Profile profile = getProfile(profile_id);

		employee.setDepartment(departmentService.getById(department_id));
		employee.setName(employeeProperties.getName());
		employee.setProfile(profile);

		return employeeRepository.save(employee);
	}

	private Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(
				String.format("Employee with id: %d does not exist.", id)));
	}

	private Profile getProfile(Long id) {
		return profileRepository.findById(id).orElseThrow(
				() -> new ProfileNotFoundException(
						String.format("Profile with id: %d does not exist.", id)));
	}
}
