package com.custorder.demo.Entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Product {
    @Id @GeneratedValue
    @CreatedBy
    @LastModifiedBy
    private Long id;
    @NotBlank(message = "Name Field Required!")
    private String name;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn
    private List<ProductComponent> productComponents = new ArrayList<>();
    private Double quantity = 0.0;
    private Double productCost = 0.0;
    @NotBlank(message = "Price Field Required!")
    private Double productPrice = 0.0;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @CreatedDate
    @LastModifiedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status;  //Raw - WIP - Finished Good

    //default constructor
    public Product(){

    }
    public Product(Long id, String name, List<ProductComponent> productComponents, Double quantity, Double productCost, Double productPrice, LocalDateTime createdAt, String status){
        this.id = id;
        this.name = name;
        this.productComponents = productComponents;
        this.quantity = quantity;
        this.productCost = productCost;
        this.productPrice = productPrice;
        this.createdAt = createdAt;
        this.status = status;
    }

    //getters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<ProductComponent> getProductComponents(){
        return this.productComponents;
    }

    public Double getQuantity(){
        return this.quantity;
    }

    public Double getProductCost(){
        return this.productCost;
    }

    public Double getProductPrice(){
        return this.productPrice;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public String getStatus(){
        return this.status;
    }


    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProductComponents(List<ProductComponent> productComponents){
        this.productComponents = productComponents;
    }

    public void addProductComponent(ProductComponent myComponent){
        this.productComponents.add(myComponent);
    }

    public void removeProductComponent(ProductComponent myComponent){
        this.productComponents.remove(myComponent);
    }

    public void setProductCost(Double productCost){
        this.productCost = productCost;
    }

    public void setProductPrice(Double productPrice){
        this.productPrice = productPrice;
    }

    public void setQuantity(Double quantity){
        this.quantity = quantity;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Double calculateProductMarginValue(){
        return this.productPrice - this.productCost;
    }


    //toString method
    public String toString(){
        return String.format("Product:\nId: %s\nName: %s\nComponent(s): %s\nProduct Price: %f\nStatus: %s\n", this.id, this.name, this.productComponents, this.productPrice, this.status);
    }
}