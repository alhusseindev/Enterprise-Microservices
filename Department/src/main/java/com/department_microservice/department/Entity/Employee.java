package com.department_microservice.department.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String employeeName;
    private String employeeRole;
    private String department;
    private Double employeeHours;
    private Double employeeOvertime;
    private String employeeEmail;
    private Integer phoneNumber;
    private LocalDateTime clockIn = LocalDateTime.now();
    private LocalDateTime clockOut = LocalDateTime.now();
    private String disciplinaryActions;
    private Double trainingHours;
    private String payType;
    private Double payRate;
    private Double paidTimeOff;
    private Double unpaidTimeOff;
    private String employeePackage;
    private String employeeStatus;
    private String comments;
    private String performanceAppraisals;
    private String employeeResponsibilities;
    private LocalDateTime joiningDate;

    public Employee(){

    }

    public Employee(Long id, String employeeName, String employeeRole, String department , String employeeEmail, Double employeeHours, Double employeeOvertime, LocalDateTime clockIn, LocalDateTime clockOut, String disciplinaryActions, Double trainingHours, String payType, Double payRate, Double paidTimeOff, Double unpaidTimeOff, String employeePackage, String employeeStatus, String comments, String performanceAppraisals, Integer phoneNumber, LocalDateTime joiningDate, String employeeResponsibilities){
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.department = department;
        this.employeeEmail = employeeEmail;
        this.employeeHours = employeeHours;
        this.employeeOvertime = employeeOvertime;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.disciplinaryActions = disciplinaryActions;
        this.trainingHours = trainingHours;
        this.payType = payType;
        this.payRate = payRate;
        this.paidTimeOff = paidTimeOff;
        this.unpaidTimeOff = unpaidTimeOff;
        this.employeePackage = employeePackage;
        this.employeeStatus = employeeStatus;
        this.comments = comments;
        this.performanceAppraisals = performanceAppraisals;
        this.phoneNumber = phoneNumber;
        this.joiningDate = joiningDate;
        this.employeeResponsibilities = employeeResponsibilities;
    }


    //Getters
    public Long getId(){
        return this.id;
    }

    public String getEmployeeName(){
        return this.employeeName;
    }

    public String getEmployeeRole(){
        return this.employeeRole;
    }

    public Double getEmployeeHours(){
        return this.employeeHours;
    }

    public Double getEmployeeOvertime(){
        return this.employeeOvertime;
    }

    public LocalDateTime getClockIn(){
        return this.clockIn;
    }

    public LocalDateTime getClockOut(){
        return this.clockOut;
    }

    public String getDisciplinaryActions(){
        return this.disciplinaryActions;
    }

    public Double getTrainingHours(){
        return this.trainingHours;
    }

    public String getPayType(){
        return this.payType;
    }

    public Double getPayRate(){
        return this.payRate;
    }

    public Double getPaidTimeOff(){
        return this.paidTimeOff;
    }

    public Double getUnpaidTimeOff(){
        return this.unpaidTimeOff;
    }

    public String getEmployeePackage(){
        return this.employeePackage;
    }

    public String getEmployeeStatus(){
        return this.employeeStatus;
    }

    public String getComments(){
        return this.comments;
    }

    public String getPerformanceAppraisals(){
        return this.performanceAppraisals;
    }

    public Integer getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getEmployeeEmail(){
        return this.employeeEmail;
    }

    public String getDepartment(){
        return this.department;
    }

    public LocalDateTime getJoiningDate(){
        return this.joiningDate;
    }

    public String getEmployeeResponsibilities(){
        return this.employeeResponsibilities;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

    public void setEmployeeRole(String employeeRole){
        this.employeeRole = employeeRole;
    }

    public void setEmployeeHours(Double employeeHours){
        this.employeeHours = employeeHours;
    }

    public void setEmployeeOvertime(Double employeeOvertime){
        this.employeeOvertime = employeeOvertime;
    }

    public void setClockIn(LocalDateTime clockIn){
        this.clockIn = clockIn;
    }

    public void setClockOut(LocalDateTime clockOut){
        this.clockOut = clockOut;
    }

    public void setDisciplinaryActions(String disciplinaryActions){
        this.disciplinaryActions = disciplinaryActions;
    }

    public void setTrainingHours(Double trainingHours){
        this.trainingHours = trainingHours;
    }

    public void setPayType(String payType){
        this.payType = payType;
    }

    public void setPayRate(Double payRate){
        this.payRate = payRate;
    }

    public void setPaidTimeOff(Double paidTimeOff){
        this.paidTimeOff = paidTimeOff;
    }

    public void setUnpaidTimeOff(Double unpaidTimeOff){
        this.unpaidTimeOff = unpaidTimeOff;
    }

    public void setEmployeePackage(String employeePackage){
        this.employeePackage = employeePackage;
    }

    public void setEmployeeStatus(String employeeStatus){
        this.employeeStatus = employeeStatus;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public void setPerformanceAppraisals(String performanceAppraisals){
        this.performanceAppraisals = performanceAppraisals;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setEmployeeEmail(String employeeEmail){
        this.employeeEmail = employeeEmail;
    }
    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setJoiningDate(LocalDateTime joiningDate){
        this.joiningDate = joiningDate;
    }

    public void setEmployeeResponsibilities(String employeeResponsibilities){
        this.employeeResponsibilities = employeeResponsibilities;
    }

    //toString method
    public String toString(){
        return "ID: " + this.id + '\n' + "Employee Name: " + this.employeeName + '\n' + "Employee Role: " + this.employeeRole + '\n' + "Department: " + this.department + '\n' + "Email: " + this.employeeEmail + '\n' + "Phone Number: " + this.phoneNumber + '\n' + "Employee Hours: " + this.employeeHours + '\n' + "Employee Overtime: " + this.employeeOvertime + '\n' + "Clock-In: " + this.clockIn + '\n' + "Clock Out: " + this.clockOut + '\n' + "Disciplinary Actions: " + this.disciplinaryActions + '\n' + "Training Hours: " + this.trainingHours + '\n' + "Pay Type: " + this.payType + '\n' + "Pay Rate: " + this.payRate + '\n' + "Paid Time-Off: " + this.paidTimeOff + '\n' + "UnPaid Time-Off: " + this.unpaidTimeOff + '\n' + "Employee Package: " + this.employeePackage + '\n' + "Status: " + this.employeeStatus + '\n' + "Comments: " + this.comments + '\n' + "Performance Appraisals: " + this.performanceAppraisals + '\n' + "Joining Date: " + this.joiningDate;
    }

}
