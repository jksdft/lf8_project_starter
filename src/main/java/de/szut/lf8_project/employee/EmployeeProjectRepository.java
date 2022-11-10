package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    public List<EmployeeProject> findAllByProject(ProjectEntity project);
    public EmployeeProject findByEmployeeIdAndProject(Long EmployeeId, ProjectEntity project);
    public List<EmployeeProject> findAllByEmployeeId(Long EmployeeId);
}

