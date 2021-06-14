package com.component.component.Entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Table
@Entity
public class Component {
    @Id @GeneratedValue
    @CreatedBy @LastModifiedBy
    private Long id;
    @NotNull(message = "Name Field Required!")
    private String name;
    @NotNull(message = "Unit Field Required!")
    private String unit;
    private Double quantity = 0.0;
    private Double componentCost = 0.0;
    @NotNull(message = "Price Field Required!")
    private Double componentPrice = 0.0;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @LastModifiedDate @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status;  //Raw - WIP - Finished Good

    //default constructor (required by spring boot)
    public Component(){

    }

    //actual constructor
    public Component(Long id, String name, String unit, Double quantity, Double componentCost, Double componentPrice, LocalDateTime createdAt, String status){
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.componentCost = componentCost;
        this.componentPrice = componentPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    //Getters
    public Long getId(){
        return this.id;
    }

    public String getname(){
        return this.name;
    }

    public String getUnit(){
        return this.unit;
    }

    public Double getQuantity(){
        return this.quantity;
    }

    public Double getComponentCost(){
        return this.componentCost;
    }

    public Double getComponentPrice(){
        return this.componentPrice;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public String getStatus(){
        return this.status;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public void setQuantity(Double quantity){
        this.quantity = quantity;
    }

    public void setComponentCost(Double componentCost){
        this.componentCost = componentCost;
    }

    public void setComponentPrice(Double componentPrice){
        this.componentPrice = componentPrice;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setStatus(String status){
        this.status = status;
    }


    public Double calculateComponentMarginValue(){
        return this.componentPrice - this.componentCost;
    }

    //toString method
    public String toString(){
        return "Component:\n" + "Id: " + this.id + "\n" + "Name: " + this.name + "\n" +"Unit: " + this.unit + "\n" + "Quantity: " + this.quantity + "\n" + "Component Price: " + this.componentPrice + "\n" + "Created At: " + this.createdAt + "\n";
    }
}


