package dev.leoduarte.api.leo.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.leoduarte.DepartmentsApi;
import dev.leoduarte.api.leo.app.persistence.DepartmentRepository;
import dev.leoduarte.api.leo.app.service.DepartmentService;
import dev.leoduarte.model.Department;
import dev.leoduarte.model.DepartmentCreation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DepartmentsController implements DepartmentsApi {

	private DepartmentRepository departmentRepository;
	private DepartmentService departmentService;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override public ResponseEntity<Department> createDepartment(@Valid DepartmentCreation departmentCreation) {
		Department department = objectMapper.convertValue(
				departmentService.createDepartment(departmentCreation),
				Department.class);
		return ResponseEntity.ok(department);
	}

	@Override public ResponseEntity<Void> deleteDepartment(Integer id) {
		return null;
	}

	@Override public ResponseEntity<List<Department>> getAll() {
		return null;
	}

	@Override public ResponseEntity<List<Department>> getById(Integer id) {
		return null;
	}

	@Override
	public ResponseEntity<Department> updateDepartment(Integer id, @Valid DepartmentCreation departmentCreation) {
		return null;
	}

	@Autowired
	public DepartmentsController(DepartmentRepository departmentRepository, DepartmentService departmentService) {
		this.departmentRepository = departmentRepository;
		this.departmentService = departmentService;
	}
}
