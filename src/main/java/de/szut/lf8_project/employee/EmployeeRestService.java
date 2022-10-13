package de.szut.lf8_project.employee;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeRestService {

    private final RestTemplate restTemplate;

    public EmployeeRestService(){
        this.restTemplate = new RestTemplate();
    }





}
