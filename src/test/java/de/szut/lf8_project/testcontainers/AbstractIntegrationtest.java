package de.szut.lf8_project.testcontainers;

import de.szut.lf8_project.employee.EmployeeProjectRepository;
import de.szut.lf8_project.project.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
@ContextConfiguration(initializers = PostgresContextInitializer.class)
public class AbstractIntegrationtest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ProjectRepository projectRepository;
    @Autowired
    protected EmployeeProjectRepository employeeProjectRepository;

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        employeeProjectRepository.deleteAll();
    }
}
