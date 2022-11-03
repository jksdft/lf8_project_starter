package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    @ManyToOne
    private ProjectEntity project;
    private String skill;
}
