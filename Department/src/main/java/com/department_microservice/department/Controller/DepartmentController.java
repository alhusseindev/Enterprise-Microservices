package com.department_microservice.department.Controller;

import com.department_microservice.department.Entity.Department;
import com.department_microservice.department.Entity.Employee;
import com.department_microservice.department.Exceptions.DepartmentException;
import com.department_microservice.department.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/departments/department")
@RestController
public class DepartmentController {
    @Autowired
    private static DepartmentRepository myDepartmentRepository;

    Department myDepartment = new Department();

    WebClient myWebClient = WebClient.create("http://localhost:8080");

    @GetMapping("/list")
    public List<Department> listDepartments(){
        return myDepartmentRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Department getDepartmentById(@PathVariable Long id) throws DepartmentException{
        return myDepartmentRepository.findById(id).orElseThrow(() -> new DepartmentException("Department Not Found!"));
    }


    @PostMapping("/new")
    public Department createDepartment(@RequestBody Department myDepartment){
        return myDepartmentRepository.save(myDepartment);
    }

    @PatchMapping("/update/{id}")
    public Department updateDepartment(@RequestBody Department myDepartment, @PathVariable Long id){
        return myDepartmentRepository.findById(id).map((department) ->{
            department.setName(myDepartment.getName());
            department.setDepartmentHead(myDepartment.getDepartmentHead());
            department.setDepartmentMembers(myDepartment.getDepartmentMembers());
            department.setPhoneNumber(myDepartment.getPhoneNumber());
            department.setEmail(myDepartment.getEmail());
            return myDepartmentRepository.save(department);
        }).orElseGet(() ->{
            myDepartment.setId(id);
            return myDepartmentRepository.save(myDepartment);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartmentById(@PathVariable Long id){
        myDepartmentRepository.deleteById(id);
    }

    @GetMapping("/count")
    public long getDepartmentCount(){
        return myDepartmentRepository.count();
    }


    @GetMapping("/employees/add")
    public void addEmployeeToDepartment(@PathVariable Long employeeId){
        Employee myEmployee = myWebClient
                .get()
                .uri("/employees/employee/get/{" + employeeId + "}")
                .retrieve()
                .bodyToMono(Employee.class).block();

        myDepartment.addEmployeeToDepartment(myEmployee);
    }


    @DeleteMapping("/employees/remove")
    public void removeEmployeeFromDepartment(@PathVariable Long employeeId){
        Employee myEmployee = myWebClient
                .get()
                .uri("/employees/employee/get/{" + employeeId + "}")
                .retrieve()
                .bodyToMono(Employee.class).block();

        myDepartment.removeEmployeeFromDepartment(myEmployee);
    }
}
