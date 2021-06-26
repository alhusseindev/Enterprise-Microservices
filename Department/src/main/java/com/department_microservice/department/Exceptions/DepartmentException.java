package com.department_microservice.department.Exceptions;
import java.lang.Exception;

public class DepartmentException extends Exception{
    public DepartmentException(String errorMessage){
        super(errorMessage);
    }
}
