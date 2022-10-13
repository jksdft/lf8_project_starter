package de.szut.lf8_project.project;

import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    public ProjectEntity create(ProjectEntity project){
        return this.projectRepository.save(project);
    }
}
