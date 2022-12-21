package dev.leoduarte.api.leo.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.leoduarte.api.leo.app.exceptions.EmployeeNotFoundException;
import dev.leoduarte.api.leo.app.exceptions.ProfileNotFoundException;
import dev.leoduarte.api.leo.app.mapper.EmployeeMapper;
import dev.leoduarte.api.leo.app.persistence.EmployeeRepository;
import dev.leoduarte.api.leo.app.persistence.ProfileRepository;
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
	private final EmployeeMapper employeeMapper;
	private final ProfileRepository profileRepository;
	private final DepartmentService departmentService;

	public Employee createEmployee(EmployeeCreation employeeCreation) {
		Profile profile = getProfile(employeeCreation.getProfileId().longValue());
		Department department = getDepartment(employeeCreation.getDepartmentId().longValue());

		Employee employee = employeeMapper.toEmployeeEntity(employeeCreation);
		employee.setProfile(profile);
		employee.setDepartment(department);
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.delete(getEmployee(id));
	}

	public List<dev.leoduarte.model.Employee> getAll() {
		return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeDto)
				.collect(Collectors.toList());
	}

	public Employee getById(Long id) {
		return getEmployee(id);
	}

	public Employee updateEmployee(long id, EmployeeProperties employeeProperties) {
		Employee employee = getEmployee(id);

		Long department_id = employeeProperties.getDepartmentId().longValue();
		Department department = departmentService.getById(department_id);

		Long profile_id = employeeProperties.getProfileId().longValue();
		Profile profile = getProfile(profile_id);

		employee.setDepartment(department);
		employee.setName(employeeProperties.getName());
		employee.setTelephone(employeeProperties.getTelephone());
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

	private Department getDepartment(Long departmentId) {
		return departmentService.getById(departmentId);
	}
}
