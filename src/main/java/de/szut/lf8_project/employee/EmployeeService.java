package de.szut.lf8_project.employee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class EmployeeService {

    private EmployeeRestService restService;

    public EmployeeService(){
        this.restService = new EmployeeRestService();
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
}
