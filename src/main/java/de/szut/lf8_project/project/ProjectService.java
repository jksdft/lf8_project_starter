package de.szut.lf8_project.project;

import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    public ProjectEntity createProject(ProjectEntity project){
        return this.projectRepository.save(project);
    }

    public void deleteProject(ProjectEntity project) {
        this.projectRepository.delete(project);
    }

    public ProjectEntity getProjectById(Long id) throws ResourceNotFoundException {
        Optional<ProjectEntity> optional = this.projectRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new ResourceNotFoundException("Project with ID " + id + " not found");
    }

    public List<ProjectEntity> getAllProjects(){
        List<ProjectEntity> projectList = projectRepository.findAll();
        return projectList;
    }

    public ProjectEntity update(ProjectEntity project){
        ProjectEntity updatedProject = getProjectById(project.getId());
        updatedProject.setProjectDescription(project.getProjectDescription());
        updatedProject.setResponsibleEmployee(project.getResponsibleEmployee());
        updatedProject.setResponsibleCustomer(project.getResponsibleCustomer());
        updatedProject.setComment(project.getComment());
        updatedProject.setRealEndDate(project.getRealEndDate());

        updatedProject = this.projectRepository.save(updatedProject);
        return updatedProject;
    }
}
