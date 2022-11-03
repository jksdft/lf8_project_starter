package de.szut.lf8_project.employee;

import de.szut.lf8_project.employee.dto.EmployeeSkillDto;
import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public EmployeeProject mapEmployeeSkillToEmployeeProject(EmployeeSkillDto dto, ProjectEntity project, EmployeeEntity employee){
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setSkill(dto.getSkill());
        employeeProject.setProject(project);
        employeeProject.setEmployeeId(employee.getId());
        return employeeProject;
    }
}
