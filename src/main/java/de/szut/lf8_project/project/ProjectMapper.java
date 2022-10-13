package de.szut.lf8_project.project;

import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

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
        dto.setId(project.getId());
        dto.setProjectDescription(project.getProjectDescription());
        dto.setResponsibleEmployeeName("Lorem Ipsum");
        dto.setCustomerId(project.getCustomerId());
        dto.setResponsibleCustomer(project.getResponsibleCustomer());
        dto.setComment(project.getComment());
        dto.setStartDate(project.getStartDate());
        dto.setExpectedEndDate(project.getExpectedEndDate());
        dto.setRealEndDate(project.getRealEndDate());
        return dto;
    }
}
