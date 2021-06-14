package com.accounting.accounting.Entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@Table
@Entity
public class Supplier {
    @Id @GeneratedValue
    private Long id;
    private String supplierTitle;
    @NotBlank(message = "First Name Required!")
    private String supplierFirstName;
    @NotBlank(message = "Last Name Required!")
    private String supplierLastName;
    private String companyName;
    @NotBlank(message = "Phone Number Required!")
    private Integer phoneNumber = 0;
    private Integer otherPhoneNumber = 0;
    @NotBlank(message = "Account Number Required!")
    private Integer accountNumber = 0;
    @Column(unique= true, nullable= false)
    @Email(message = "Email Must Be A Valid Email!")
    @NotBlank(message = "Email Field Required!")
    private String email;
    private String address;
    private String website;
    private Double hourlyRate = 0.0;
    private String typeOfGoods;
    private String paymentTerms;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @CreatedDate
    @LastModifiedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    private String notes;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private List<Product> products;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private List<ProductComponent> components;


    // Default Constructor (Constructor Injection)
    public Supplier(){

    }

    //Actual Constructor
    public Supplier(Long id, String supplierTitle, String supplierFirstName, String supplierLastName, String companyName, Integer phoneNumber, Integer otherPhoneNumber, Integer accountNumber, String email, String address, String website, Double hourlyRate, String typeOfGoods, String paymentTerms, String notes, List<Product> products, List<ProductComponent> components, LocalDateTime createdAt){
        this.id = id;
        this.supplierTitle = supplierTitle;
        this.supplierFirstName = supplierFirstName;
        this.supplierLastName = supplierLastName;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumber = otherPhoneNumber;
        this.companyName = companyName;
        this.accountNumber = accountNumber;
        this.email = email;
        this.address = address;
        this.website = website;
        this.hourlyRate = hourlyRate;
        this.typeOfGoods = typeOfGoods;
        this.paymentTerms = paymentTerms;
        this.notes = notes;
        this.createdAt = createdAt;
        this.products = products;
        this.components = components;
    }

    //Getters
    public Long getId(){
        return this.id;
    }

    public String getSupplierTitle(){
        return this.supplierTitle;
    }

    public String getSupplierFirstname(){
        return this.supplierFirstName;
    }

    public String getSupplierLastName(){
        return this.supplierLastName;
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

    public String getWebsite(){
        return this.website;
    }

    public Double getHourlyRate(){
        return this.hourlyRate;
    }

    public String getTypeOfGoods(){
        return this.typeOfGoods;
    }

    public String getPaymentTerms(){
        return this.paymentTerms;
    }

    public String getNotes(){
        return this.notes;
    }

    public List<Product> getProducts(){
        return this.products;
    }

    public List<ProductComponent> getComponents(){
        return this.components;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setSupplierTitle(String supplierTitle){
        this.supplierTitle = supplierTitle;
    }

    public void setSupplierFirstName(String supplierFirstName){
        this.supplierFirstName = supplierFirstName;
    }

    public void setSupplierLastName(String supplierLastName){
        this.supplierLastName = supplierLastName;
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

    public void setWebsite(String website){
        this.website = website;
    }

    public void setHourlyRate(Double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    public void setTypeOfGoods(String typeOfGoods){
        this.typeOfGoods = typeOfGoods;
    }

    public void setPaymentTerms(String paymentTerms){
        this.paymentTerms = paymentTerms;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public void setComponents(List<ProductComponent> components){
        this.components = components;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    //toString method
    public String toString(){
        return String.format("Supplier Id: %d\nSupplier Title: %s\nSupplier First Name: %s\nSupplier Last Name: %s\nCompany Name: %s\nphoneNumber: %d\nOther Phone Number: %d\nAccount Number: %d\nEmail: %s\nAddress: %s\nWebsite: %s\nHourly Rate: %f\nType Of Goods: %s\nPayment Terms: %s\nNotes: %s\nProducts List: %s\nComponents List: %s",
                this.id, this.supplierTitle, this.supplierFirstName, this.supplierLastName, this.companyName, this.phoneNumber, this.otherPhoneNumber, this.accountNumber, this.email, this.address, this.website, this.hourlyRate, this.typeOfGoods, this.paymentTerms, this.notes, this.products, this.components) + "Created At: " + this.createdAt + "\n";
    }

}
