package de.szut.lf8_project.project;

import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.LinkedList;

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

    @GetMapping
    public ResponseEntity<List<GetProjectDto>> getAllProjects(){
        List<ProjectEntity> projectList = projectService.getAllProjects();
        List<GetProjectDto> dtoList = new LinkedList<>();
        for (ProjectEntity project:projectList) {
            dtoList.add(projectMapper.mapProjectEntityToGetProjectDto(project));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProjectDto> getProject(@PathVariable Long id) {
        ProjectEntity project = this.projectService.getProjectById(id);
        GetProjectDto dto = this.projectMapper.mapProjectEntityToGetProjectDto(project);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetProjectDto> updateProjectEntity(@PathVariable Long id, @Valid @RequestBody AddProjectDto entryToUpdate) {
        ProjectEntity updateProject = this.projectMapper.mapAddProjectDtoToProjectEntity(entryToUpdate);
        updateProject.setId(id);
        updateProject = this.projectService.update(updateProject);
        GetProjectDto request = this.projectMapper.mapProjectEntityToGetProjectDto(updateProject);
        return new ResponseEntity<>(request,HttpStatus.OK);
    }
}