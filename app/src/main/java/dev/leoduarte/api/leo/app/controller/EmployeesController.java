package dev.leoduarte.api.leo.app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.EmployeesApi;
import dev.leoduarte.api.leo.app.service.EmployeeService;
import dev.leoduarte.model.Employee;
import dev.leoduarte.model.EmployeeCreation;
import dev.leoduarte.model.EmployeeProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class EmployeesController implements EmployeesApi {
	private final EmployeeService employeeService;
	private final ObjectMapper objectMapper;

	@Override
	public ResponseEntity<Employee> createEmployee(@Valid EmployeeCreation employeeCreation) {
		Employee employee = objectMapper.convertValue(
				employeeService.createEmployee(employeeCreation),
				Employee.class);
		return ResponseEntity.ok(employee);
	}

	@Override
	public ResponseEntity<Void> deleteEmployee(Integer id) {
		log.info("Delete employee id: {}", id);
		employeeService.deleteEmployee(id.longValue());
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> employees = employeeService.getAll();
		return ResponseEntity.ok(employees);
	}

	@Override
	public ResponseEntity<Employee> getById(Integer id) {
		Employee employee = objectMapper.convertValue(
				employeeService.getById(id.longValue()),
				Employee.class);
		return ResponseEntity.ok(employee);
	}

	@Override
	public ResponseEntity<List<Employee>> listEmployees(@NotNull @Valid Integer page, @NotNull @Valid Integer quantity,
			@Valid String ordination) {
		// TODO Pageable
		return null;
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(Integer id,
			@Valid EmployeeProperties employeeProperties) {
		Employee employee = objectMapper.convertValue(
				employeeService.updateEmployee(id.longValue(), employeeProperties),
				Employee.class);
		return ResponseEntity.ok(employee);
	}
}
