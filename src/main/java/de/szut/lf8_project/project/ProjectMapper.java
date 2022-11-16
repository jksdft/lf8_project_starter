package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeeService;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    @Autowired
    private EmployeeService employeeService;

    public ProjectEntity mapAddProjectDtoToProjectEntity(AddProjectDto dto){
        ProjectEntity project = new ProjectEntity();

        project.setProjectDescription(dto.getProjectDescription());
        project.setResponsibleEmployee(dto.getResponsibleEmployee());
        project.setCustomerId(dto.getCustomerId());
        project.setResponsibleCustomer(dto.getResponsibleCustomer());
        project.setComment(dto.getComment());
        project.setStartDate(dto.getStartDate());
        project.setExpectedEndDate(dto.getExpectedEndDate());

        return project;
    }

    public GetProjectDto mapProjectEntityToGetProjectDto(ProjectEntity project){
        GetProjectDto dto = new GetProjectDto();
        EmployeeEntity responsibleEmployee = employeeService.getEmployeeById(project.getResponsibleEmployee());
        dto.setId(project.getId());
        dto.setProjectDescription(project.getProjectDescription());
        dto.setResponsibleEmployeeName(String.format("%s %s", responsibleEmployee.getFirstName(),responsibleEmployee.getLastName()));
        dto.setCustomerId(project.getCustomerId());
        dto.setResponsibleCustomer(project.getResponsibleCustomer());
        dto.setComment(project.getComment());
        dto.setStartDate(project.getStartDate());
        dto.setExpectedEndDate(project.getExpectedEndDate());
        dto.setRealEndDate(project.getRealEndDate());
        return dto;
    }
}
