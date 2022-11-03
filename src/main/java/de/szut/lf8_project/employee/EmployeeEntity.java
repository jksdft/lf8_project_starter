package de.szut.lf8_project.employee;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeEntity {
    private long id;
    private String lastName;
    private String firstName;
    private String street;
    private String postcode;
    private String city;
    private String phone;
    private List<String> skillSet;
}
