package de.szut.lf8_project.integrationstest.project;

import de.szut.lf8_project.testcontainers.AbstractIntegrationtest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CreateProjectIT extends AbstractIntegrationtest {

    @Test
    void createProject() throws Exception {
        final String content = """
                {"projectDescription": "Test","responsibleEmployee": "10","customerId": "1","responsibleCustomer": "Peter","comment": "Test","startDate":"2007-12-03T10:15:30","expectedEndDate":"2007-12-03T10:15:30"}""";
        final var contentAsString = this.mockMvc.perform(post("/project").content(content).contentType(MediaType.APPLICATION_JSON));
    }
}
