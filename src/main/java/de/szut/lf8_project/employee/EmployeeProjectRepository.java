package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    public List<EmployeeProject> findAllByProject(ProjectEntity project);
}
