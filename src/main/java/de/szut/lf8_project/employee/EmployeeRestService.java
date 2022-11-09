package de.szut.lf8_project.employee;

import de.szut.lf8_project.employee.dto.AuthenticationDto;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeRestService {

    private final RestTemplate restTemplate;
    private final String url;

    public EmployeeRestService() {
        this.restTemplate = new RestTemplate();
        this.url = "https://employee.szut.dev";
    }


    public EmployeeEntity getEmployeeById(Long id){
        ResponseEntity<EmployeeEntity> response = this.restTemplate.exchange(this.url + "/employees/" + id, HttpMethod.GET, this.getHttpEntityForEmployeeService(), EmployeeEntity.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        throw new ResourceNotFoundException("Employee with id " + id + " not found");
    }

    private HttpEntity getHttpEntityForEmployeeService(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.authenticate().getAccessToken());
        return new HttpEntity(headers);
    }

    private AuthenticationDto authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> httpEntity = new HttpEntity<>("grant_type=password&client_id=employee-management-service&username=user&password=test", headers);
        ResponseEntity<AuthenticationDto> response = this.restTemplate.exchange("https://keycloak.szut.dev/auth/realms/szut/protocol/openid-connect/token", HttpMethod.POST, httpEntity, AuthenticationDto.class);
        return response.getBody();
    }

}
