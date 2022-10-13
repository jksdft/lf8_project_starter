package de.szut.lf8_project.project;

import org.springframework.stereotype.Service;

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
