package de.szut.lf8_project.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetProjectDto {
    private Long id;
    private String projectDescription;
    private String responsibleEmployeeName;
    private Long customerId;
    private String responsibleCustomer;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime realEndDate;
}
