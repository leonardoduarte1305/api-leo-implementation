package dev.leoduarte.api.leo.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leoduarte.api.leo.app.persistence.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
