package de.szut.lf8_project.employee;

import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import io.swagger.v3.oas.integration.api.OpenApiReader;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeRestService {

    private final RestTemplate restTemplate;
    private final KeycloakRestTemplate keycloakRestTemplate;
    private final String url;


    public EmployeeRestService() {
        this.restTemplate = new RestTemplate();
        this.keycloakRestTemplate = new KeycloakRestTemplate(new KeycloakClientRequestFactory());
        this.url = "https://employee.szut.dev";
    }


    public EmployeeEntity getEmployeeById(Long id){
        ResponseEntity<EmployeeEntity> response = this.keycloakRestTemplate.getForEntity(this.url + "/employees/" + id, EmployeeEntity.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        throw new ResourceNotFoundException("Employee with id " + id + " not found");
    }

}
