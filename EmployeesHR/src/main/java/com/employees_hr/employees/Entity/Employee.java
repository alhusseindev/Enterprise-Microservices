package com.employees_hr.employees.Entity;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Table
@Entity
public class Employee{
    @Id
    @GeneratedValue
    private Long id;
    private String employeeName;
    private String employeeRole;
    private Double employeeHours;
    private Double employeeOvertime;
    private LocalDateTime clockIn = LocalDateTime.now();
    private LocalDateTime clockOut = LocalDateTime.now();
    private String disciplinaryActions;
    private Double trainingHours;
    private String payType;
    private Double payRate;
    private Double paidTimeOff;
    private Double unpaidTimeOff;
    private Double compensation;
    private String employeePackage;
    private String employeeStatus;
    private String comments;
    private String performanceAppraisals;


    public Employee(){

    }

    public Employee(Long id, String employeeName, String employeeRole, Double employeeHours, Double employeeOvertime, LocalDateTime clockIn, LocalDateTime clockOut, String disciplinaryActions, Double trainingHours, String payType, Double payRate, Double paidTimeOff, Double unpaidTimeOff, Double compensation, String employeePackage, String employeeStatus, String comments, String performanceAppraisals){
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
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
        this.compensation = compensation;
        this.employeePackage = employeePackage;
        this.employeeStatus = employeeStatus;
        this.comments = comments;
        this.performanceAppraisals = performanceAppraisals;
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

    public Double getCompensation(){
        return this.compensation;
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

    public void setCompensation(Double compensation){
        this.compensation = compensation;
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

    //toString method
    public String toString(){

    }

}
