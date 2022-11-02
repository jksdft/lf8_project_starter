package de.szut.lf8_project.project;

import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper){
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PostMapping
    public ResponseEntity<GetProjectDto> createProject(@RequestBody@Valid AddProjectDto dto){
        ProjectEntity project = projectMapper.mapAddProjectDtoToProjectEntity(dto);
        project = projectService.createProject(project);
        GetProjectDto getDto = this.projectMapper.mapProjectEntityToGetProjectDto(project);
        return new ResponseEntity<>(getDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProjectDto> getProject(@PathVariable Long id) {
        ProjectEntity project = this.projectService.getProjectById(id);
        GetProjectDto dto = this.projectMapper.mapProjectEntityToGetProjectDto(project);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GetProjectDto> refreshProject (@RequestBody GetProjectDto dtoToUpdate){
        Long id = dtoToUpdate.getId();

    }
}
