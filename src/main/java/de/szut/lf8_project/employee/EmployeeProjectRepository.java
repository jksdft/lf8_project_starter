package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}
