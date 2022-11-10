package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeeProject;
import de.szut.lf8_project.employee.EmployeeProjectRepository;
import de.szut.lf8_project.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeProjectRepository employeeProjectRepository;
    private final EmployeeService employeeService;

    public ProjectService(ProjectRepository projectRepository, EmployeeProjectRepository employeeProjectRepository, EmployeeService employeeService){
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository;
        this.employeeService = employeeService;
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

    public List <EmployeeEntity> getEmployeesFromProject(ProjectEntity project){
        List <EmployeeEntity> employees = new ArrayList<>();
        List <EmployeeProject> employeeProjectList = employeeProjectRepository.findAllByProject(project);
        for(EmployeeProject e : employeeProjectList){
            employees.add(employeeService.getEmployeeById(e.getEmployeeId()));
        }
        return employees;
    }

    public void deleteEmployeeFromProject(ProjectEntity project, Long employeeId){
        employeeProjectRepository.deleteEmployeeProjectByEmployeeIdAndProject(employeeId, project);
    }
}