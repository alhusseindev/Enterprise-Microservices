package com.accounting.accounting.Entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String customerTitle;
    @NotBlank(message = "First Name Required!")
    private String customerFirstName;
    @NotBlank(message = "Last Name Required!")
    private String customerLastName;
    private String companyName;
    @NotBlank(message = "Phone Number Required!")
    private Integer phoneNumber = 0;
    private Integer otherPhoneNumber = 0;
    private Integer accountNumber = 0;
    @Column(unique= true, nullable= false)
    private String email;
    private String address;
    private String paymentTerms;
    private String notes;
    private Double customerWorth = 0.0;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @CreatedDate @LastModifiedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany
    @JoinColumn
    private List<CustOrder> custOrders;

    // Default Constructor (Constructor Injection)
    public Customer(){

    }

    //Actual Constructor
    public Customer(Long id, String customerTitle, String customerFirstName, String customerLastName, String companyName, Integer phoneNumber, Integer otherPhoneNumber, Integer accountNumber, String email, String address, String paymentTerms, String notes, List<CustOrder> custOrders, Double customerWorth, LocalDateTime createdAt){
        this.id = id;
        this.customerTitle = customerTitle;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumber = otherPhoneNumber;
        this.companyName = companyName;
        this.accountNumber = accountNumber;
        this.email = email;
        this.address = address;
        this.paymentTerms = paymentTerms;
        this.notes = notes;
        this.custOrders = custOrders;
        this.customerWorth = customerWorth;
        this.createdAt = createdAt;
    }

    //Getters
    public Long getId(){
        return this.id;
    }

    public String getCustomerTitle(){
        return this.customerTitle;
    }

    public String getCustomerFirstname(){
        return this.customerFirstName;
    }

    public String getCustomerLastName(){
        return this.customerLastName;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public Integer getPhoneNumber(){
        return this.phoneNumber;
    }

    public Integer getOtherPhoneNumber(){
        return this.otherPhoneNumber;
    }

    public Integer getAccountNumber(){
        return this.accountNumber;
    }

    public String getEmail(){
        return this.email;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPaymentTerms(){
        return this.paymentTerms;
    }

    public String getNotes(){
        return this.notes;
    }

    public List<CustOrder> getCustOrders(){
        return this.custOrders;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public Integer getTotalNoOfOrders(){
        return this.custOrders.size();
    }


    public Double getCustomerWorth(){
        for(CustOrder customerOrders : custOrders){
            this.customerWorth = this.customerWorth + customerOrders.getTotalCharge();
        }
        return this.customerWorth;
    }



    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setCustomerTitle(String customerTitle){
        this.customerTitle = customerTitle;
    }

    public void setCustomerFirstName(String customerFirstName){
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName){
        this.customerLastName = customerLastName;
    }

    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setOtherPhoneNumber(Integer otherPhoneNumber){
        this.otherPhoneNumber = otherPhoneNumber;
    }

    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void addOrderToCustOrders(CustOrder customerOrder){
        this.custOrders.add(customerOrder);
    }

    public void removeOrderFromCustOrders(CustOrder customerOrder){
        this.custOrders.remove(customerOrder);
    }

    public void removeAllOrdersFromCustomerOrders(List<CustOrder> allCustOrders){
        this.custOrders.removeAll(allCustOrders);
    }

    public void setPaymentTerms(String paymentTerms){
        this.paymentTerms = paymentTerms;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setCustOrders(List<CustOrder> custOrders){
        this.custOrders = custOrders;
    }

    public void setCustomerWorth(Double customerWorth){
        this.customerWorth = customerWorth;
    }

    //toString method
    public String toString(){
        return String.format("Customer Id: %d\nCustomer Title: %s\nCustomer First Name: %s\nCustomer Last Name: %s\nCompany Name: %s\nphoneNumber: %d\nOther Phone Number: %d\nAccount Number: %d\nEmail: %s\nAddress: %s\nPayment Terms: %s\nNotes: %s\nOrders List: %s\n",
                this.id, this.customerTitle, this.customerFirstName, this.customerLastName, this.companyName, this.phoneNumber, this.otherPhoneNumber, this.accountNumber, this.email, this.address, this.paymentTerms, this.notes, this.custOrders) + "Created At: " + this.createdAt + "\n";
    }


}
