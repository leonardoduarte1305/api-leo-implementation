package dev.leoduarte.api.leo.app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.leoduarte.EmployeesApi;
import dev.leoduarte.model.Employee;
import dev.leoduarte.model.EmployeeCreation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeesController implements EmployeesApi {

	@Override public ResponseEntity<Employee> createEmployee(@Valid EmployeeCreation employeeCreation) {
		return null;
	}

	@Override public ResponseEntity<Void> deleteEmployee(Integer id) {
		return null;
	}

	@Override public ResponseEntity<List<Employee>> getAll() {
		return null;
	}

	@Override public ResponseEntity<List<Employee>> getById(Integer id) {
		return null;
	}

	@Override
	public ResponseEntity<List<Employee>> listEmployees(@NotNull @Valid Integer page, @NotNull @Valid Integer quantity,
			@Valid String ordination) {
		return null;
	}

	@Override public ResponseEntity<Employee> updateEmployee(Integer id, @Valid EmployeeCreation employeeCreation) {
		return null;
	}
}
