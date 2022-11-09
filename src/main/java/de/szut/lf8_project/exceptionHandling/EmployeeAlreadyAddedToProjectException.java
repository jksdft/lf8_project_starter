package de.szut.lf8_project.exceptionHandling;

public class EmployeeAlreadyAddedToProjectException extends RuntimeException{
    public EmployeeAlreadyAddedToProjectException(String message){
        super(message);
    }
}
