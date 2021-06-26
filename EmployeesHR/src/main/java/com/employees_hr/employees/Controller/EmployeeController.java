package com.employees_hr.employees.Controller;


import com.employees_hr.employees.Entity.Employee;
import com.employees_hr.employees.Exceptions.EmployeeException;
import com.employees_hr.employees.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/employees/employee")
@RestController
public class EmployeeController {

    @Autowired
    private static EmployeeRepository myEmployeeRepository;

    @GetMapping("/list")
    public List<Employee> listEmployees(){
        return myEmployeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws EmployeeException{
        return myEmployeeRepository.findById(id).orElseThrow(() -> new EmployeeException("Could Not Find Employee!"));
    }

    @PostMapping("/new")
    public Employee createNewEmployee(@RequestBody Employee myEmployee){
        return myEmployeeRepository.save(myEmployee);
    }

    @PatchMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee myEmployee, @PathVariable Long id){
        return myEmployeeRepository.findById(id).map((employee) ->{
            employee.setEmployeeName(myEmployee.getEmployeeName());
            employee.setEmployeeRole(myEmployee.getEmployeeRole());
            employee.setJoiningDate(myEmployee.getJoiningDate());
            employee.setDepartment(myEmployee.getDepartment());
            employee.setEmployeeEmail(myEmployee.getEmployeeEmail());
            employee.setPhoneNumber(myEmployee.getPhoneNumber());
            employee.setEmployeeHours(myEmployee.getEmployeeHours());
            employee.setEmployeeHours(myEmployee.getEmployeeHours());
            employee.setEmployeeOvertime(myEmployee.getEmployeeOvertime());
            employee.setClockIn(myEmployee.getClockIn());
            employee.setClockOut(myEmployee.getClockOut());
            employee.setDisciplinaryActions(myEmployee.getDisciplinaryActions());
            employee.setTrainingHours(myEmployee.getTrainingHours());
            employee.setPayType(myEmployee.getPayType());
            employee.setPayRate(myEmployee.getPayRate());
            employee.setPaidTimeOff(myEmployee.getPaidTimeOff());
            employee.setUnpaidTimeOff(myEmployee.getUnpaidTimeOff());
            employee.setEmployeePackage(myEmployee.getEmployeePackage());
            employee.setEmployeeStatus(myEmployee.getEmployeeStatus());
            employee.setComments(myEmployee.getComments());
            employee.setEmployeeResponsibilities(myEmployee.getEmployeeResponsibilities());
            employee.setPerformanceAppraisals(myEmployee.getPerformanceAppraisals());
            return myEmployeeRepository.save(employee);
        }).orElseGet(() ->{
            myEmployee.setId(id);
            return myEmployeeRepository.save(myEmployee);
        });
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        myEmployeeRepository.deleteById(id);
    }

    @GetMapping("/count")
    public long getEmployeeCount(){
        return myEmployeeRepository.count();
    }
    
}
