package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.*;
import de.szut.lf8_project.employee.dto.EmployeeSkillDto;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.LinkedList;

@RestController
@RequestMapping(value = "project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    private EmployeeService employeeService;

    private EmployeeMapper employeeMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, EmployeeService employeeService, EmployeeMapper employeeMapper){
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Operation(summary = "Creates a new Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created project", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<GetProjectDto> createProject(@RequestBody@Valid AddProjectDto dto){
        ProjectEntity project = projectMapper.mapAddProjectDtoToProjectEntity(dto);
        project = projectService.createProject(project);
        GetProjectDto getDto = this.projectMapper.mapProjectEntityToGetProjectDto(project);
        return new ResponseEntity<>(getDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "delete project"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "201", description = "project not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        ProjectEntity project = projectService.getProjectById(id);
        projectService.deleteProject(project);
        return new ResponseEntity<>("Project with ID " + id + " deleted", HttpStatus.OK);
    }

    @Operation(summary = "Get all Projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get project", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<GetProjectDto>> getAllProjects(){
        List<ProjectEntity> projectList = projectService.getAllProjects();
        List<GetProjectDto> dtoList = new LinkedList<>();
        for (ProjectEntity project:projectList) {
            dtoList.add(projectMapper.mapProjectEntityToGetProjectDto(project));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Operation(summary = "Find a Project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found project", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetProjectDto> getProject(@PathVariable Long id) {
        ProjectEntity project = this.projectService.getProjectById(id);
        GetProjectDto dto = this.projectMapper.mapProjectEntityToGetProjectDto(project);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Update a Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project updated", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetProjectDto> updateProjectEntity(@PathVariable Long id, @Valid @RequestBody AddProjectDto entryToUpdate) {
        ProjectEntity updateProject = this.projectMapper.mapAddProjectDtoToProjectEntity(entryToUpdate);
        updateProject.setId(id);
        updateProject = this.projectService.update(updateProject);
        GetProjectDto request = this.projectMapper.mapProjectEntityToGetProjectDto(updateProject);
        return new ResponseEntity<>(request,HttpStatus.OK);
    }

    @Operation(summary = "Add a employee to a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project updated", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "employee requires the requested skill", content = @Content)})
    @PutMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<String> addEmployeeToProject(@PathVariable Long projectId, @PathVariable Long employeeId, @Valid@RequestBody EmployeeSkillDto skill){
        ProjectEntity project = projectService.getProjectById(projectId);
        EmployeeEntity employee = employeeService.getEmployeeById(employeeId);
        if(!employeeService.hasSkill(employee, skill.getSkill())){
            return new ResponseEntity<>("Employee requires skill " + skill.getSkill(), HttpStatus.NOT_FOUND);
        }
        EmployeeProject employeeProject = employeeMapper.mapEmployeeSkillToEmployeeProject(skill, project, employee);
        employeeProject = employeeService.addEmployeeToProject(employeeProject);
        return new ResponseEntity<>("Employee added to Project", HttpStatus.OK);
    }

    @Operation(summary = "Get a list of all employees working on a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "employee list", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetProjectDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content)})
    @GetMapping("/{id}/employee")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployeesOfProject(@PathVariable Long id){
        ProjectEntity project = this.projectService.getProjectById(id);
        List<EmployeeEntity> employeeList = projectService.getEmployeesFromProject(project);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @Operation(summary = "remove employee from a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "emloyee removed", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "employee not found at the Project", content = @Content)
    })
    @DeleteMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId){
        ProjectEntity project = this.projectService.getProjectById(projectId);
        projectService.deleteEmployeeFromProject(project, employeeId);
        return new ResponseEntity<>("Employee was deleted", HttpStatus.OK);
    }
}