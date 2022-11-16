package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import de.szut.lf8_project.project.ProjectMapper;
import de.szut.lf8_project.project.dto.GetProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private final ProjectMapper projectMapper;

    public EmployeeController(EmployeeService employeeService, ProjectMapper projectMapper) {
        this.employeeService = employeeService;
        this.projectMapper = projectMapper;
    }

    @Operation(summary = "Get a list of all projects the employee is working on")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project list", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<GetProjectDto>> getAllProjectsFromEmployee(@PathVariable Long employeeId){
        List <ProjectEntity> allProjects =  employeeService.getAllProjectsFromEmployee(employeeId);
        List <GetProjectDto> projectDTO = new ArrayList<>();

        for (ProjectEntity e : allProjects){
            projectDTO.add(projectMapper.mapProjectEntityToGetProjectDto(e));
        }
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }
}
