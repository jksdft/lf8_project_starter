package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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
        return employeeEntity;
        //return this.restService.getEmployeeById(id);
    }

    public EmployeeProject addEmployeeToProject(EmployeeProject employeeProject){
        return this.employeeProjectRepository.save(employeeProject);
    }

    public boolean hasSkill(EmployeeEntity employee, String skill){
        return employee.getSkillSet().contains(skill);
    }
}
