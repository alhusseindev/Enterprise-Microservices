package com.department_microservice.department.Entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Department {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String departmentHead;
    @OneToMany
    @JoinColumn
    private List<Employee> departmentMembers;
    public Integer phoneNumber;
    public String email;

    public Department(){

    }


    public Department(Long id, String name, String departmentHead, List<Employee> departmentMembers, Integer phoneNumber, String email){
        this.id = id;
        this.name = name;
        this.departmentHead = departmentHead;
        this.departmentMembers = departmentMembers;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //getters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDepartmentHead(){
        return this.departmentHead;
    }

    public List<Employee> getDepartmentMembers(){
        return this.departmentMembers;
    }

    public Integer getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getEmail(){
        return this.email;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDepartmentHead(String departmentHead){
        this.departmentHead = departmentHead;
    }

    public void setDepartmentMembers(List<Employee> departmentMembers){
        this.departmentMembers = departmentMembers;
    }

    public void addEmployeeToDepartment(Employee myEmployee){
        this.departmentMembers.add(myEmployee);
    }

    public void removeEmployeeFromDepartment(Employee myEmployee){
        this.departmentMembers.remove(myEmployee);
    }

    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //toString
    public String toString(){
        return "Department ID: " + this.id + '\n' + "Department Name: " + this.name + '\n' + "Department Head: " + this.departmentHead + '\n' + "Department Members: " + this.departmentMembers + "Phone Number: " + this.phoneNumber + '\n' + "Email: " + this.email + '\n';
    }


}
