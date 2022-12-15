package dev.leoduarte.api.leo.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.DepartmentsApi;
import dev.leoduarte.api.leo.app.service.DepartmentService;
import dev.leoduarte.model.Department;
import dev.leoduarte.model.DepartmentCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class DepartmentsController implements DepartmentsApi {
	private final DepartmentService departmentService;
	private final ObjectMapper objectMapper;

	@Override
	public ResponseEntity<Department> createDepartment(@Valid DepartmentCreation departmentCreation) {
		Department department = objectMapper.convertValue(
				departmentService.createDepartment(departmentCreation),
				Department.class);
		return ResponseEntity.ok(department);
	}

	@Override
	public ResponseEntity<Void> deleteDepartment(Integer id) {
		log.info("Delete department id: {}", id);
		departmentService.deleteDepartment(id.longValue());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Department>> getAll() {
		List<Department> departments = departmentService.getAll();
		return ResponseEntity.ok(departments);
	}

	@Override
	public ResponseEntity<Department> getById(Integer id) {
		Department department = objectMapper.convertValue(
				departmentService.getById(id.longValue()),
				Department.class);
		return ResponseEntity.ok(department);
	}

	@Override
	public ResponseEntity<Department> updateDepartment(Integer id, @Valid DepartmentCreation departmentCreation) {
		Department department = objectMapper.convertValue(
				departmentService.updateDepartment(id.longValue(), departmentCreation),
				Department.class);
		return ResponseEntity.ok(department);
	}
}
