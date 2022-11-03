package de.szut.lf8_project.project;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectDescription;
    private Long responsibleEmployee;
    private Long customerId;
    private String responsibleCustomer;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime realEndDate;
}
