package de.szut.lf8_project.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddProjectDto {
    private String projectDescription;
    private Long responsibleEmployee;
    private Long customerId;
    private String responsibleCustomer;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
}
