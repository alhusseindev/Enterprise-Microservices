package com.accounting.accounting.Entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
public class Expenses {
    @Id @GeneratedValue
    private Long id;
    @CreatedDate
    @LastModifiedDate
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String bankAccount; //companys' account where the money will be pulled out of
    private String payee; //the person whom which the money will be paid to
    private Double moneyInAccount = 0.0;
    private Double outstandingBalance = 0.0;
    private Double expenseValue = 0.0;
    private String typeOfExpense;  //transportation cost, etc...
    private String expenseDescription;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @CreatedDate
    @LastModifiedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    //default constructor
    public Expenses(){

    }

    public Expenses(Long id, LocalDateTime paymentDate, String paymentMethod, String bankAccount, String payee, Double outstandingBalance, Double expenseValue, Double moneyInAccount, String typeOfExpense, String expenseDescription, LocalDateTime createdAt){
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.bankAccount = bankAccount;
        this.payee = payee;
        this.expenseValue = expenseValue;
        this.typeOfExpense = typeOfExpense;
        this.expenseDescription = expenseDescription;
        this.moneyInAccount = moneyInAccount;
        this.outstandingBalance = outstandingBalance;
        this.createdAt = createdAt;
    }


    //getters
    public Long getId(){
        return this.id;
    }

    public String getPaymentMethod(){
        return this.paymentMethod;
    }

    public String getBankAccount(){
        return this.bankAccount;
    }
    public String getPayee(){
        return this.payee;
    }

    public Double getOutstandingBalance(){
        return this.outstandingBalance;
    }

    public Double getExpenseValue(){
        return this.expenseValue;
    }

    public String getTypeOfExpense(){
        return this.typeOfExpense;
    }

    public Double getMoneyInAccount(){
        return this.moneyInAccount;
    }

    public String getExpenseDescription(){
        return this.expenseDescription;
    }
    public LocalDateTime getPaymentDate(){
        return this.paymentDate;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setExpenseValue(Double expenseValue){
        this.expenseValue = expenseValue;
    }

    public void setPaymentDate(LocalDateTime paymentDate){
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void setBankAccount(String bankAccount){
        this.bankAccount = bankAccount;
    }

    public void setPayee(String payee){
        this.payee = payee;
    }

    public void setOutstandingBalance(Double outstandingBalance){
        this.outstandingBalance = outstandingBalance;
    }

    public void setMoneyInAccount(Double moneyInAccount){
        this.moneyInAccount = moneyInAccount;
    }

    public void setTypeOfExpense(String typeOfExpense){
        this.typeOfExpense = typeOfExpense;
    }

    public void setExpenseDescription(String expenseDescription){
        this.expenseDescription = expenseDescription;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    //toString method
    public String toString(){
        return ("Expense\n" + "ID: " + this.id + "\n" + "Payment Date: " +
                this.paymentDate + "\n" + "Payment Method: " +
                this.paymentMethod + "\n" + "Bank Account: " +
                this.bankAccount + "\n" + "Payee: " +
                this.payee + "\n" + "Expense Value: " +
                this.expenseValue + "\n" + "Money In Account: " + this.moneyInAccount + "\n" + "Type Of Expense: " +
                this.typeOfExpense + "\n" + "Expense Description: " +
                this.expenseDescription + "\n" + "Outstanding Balance: " +
                this.outstandingBalance + "\n" + "Created At: " + this.createdAt + "\n");
    }
}