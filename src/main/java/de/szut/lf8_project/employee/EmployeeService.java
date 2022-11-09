package de.szut.lf8_project.employee;

import de.szut.lf8_project.exceptionHandling.EmployeeAlreadyAddedToProjectException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private EmployeeRestService restService;

    private EmployeeProjectRepository employeeProjectRepository;

    public EmployeeService(EmployeeProjectRepository employeeProjectRepository){
        this.restService = new EmployeeRestService();
        this.employeeProjectRepository = employeeProjectRepository;
    }

    public EmployeeEntity getEmployeeById(Long id){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(id);
        employeeEntity.setFirstName("123");
        employeeEntity.setLastName("456");
        employeeEntity.setStreet("Teststraße 1");
        employeeEntity.setCity("Test");
        employeeEntity.setPostcode("12345");
        List<String> skillSet = new ArrayList<>();
        skillSet.add("Java");
        skillSet.add("SQL");
        employeeEntity.setSkillSet(skillSet);
        // return employeeEntity;
        return this.restService.getEmployeeById(id);
    }

    public EmployeeProject addEmployeeToProject(EmployeeProject employeeProject){
        List<EmployeeProject> employeeProjects = this.employeeProjectRepository.findAllByProject(employeeProject.getProject());
        for(EmployeeProject e : employeeProjects){
            if(e.getEmployeeId() == employeeProject.getEmployeeId()){
                throw new EmployeeAlreadyAddedToProjectException("Employee with ID " + employeeProject.getEmployeeId() + " already added to Project with ID " + employeeProject.getProject().getId());
            }
        }
        return this.employeeProjectRepository.save(employeeProject);
    }

    public boolean hasSkill(EmployeeEntity employee, String skill){
        return employee.getSkillSet().contains(skill);
    }
}
