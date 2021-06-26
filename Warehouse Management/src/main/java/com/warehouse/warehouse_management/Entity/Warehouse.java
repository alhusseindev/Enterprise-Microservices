package com.warehouse.warehouse_management.Entity;

import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Table
@Entity
public class Warehouse{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double quantity;
    @ElementCollection
    @JoinColumn
    private List<Object> storedItems;
    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status;
    @ElementCollection
    @JoinColumn
    private Map<Object, Boolean> itemShipped = new HashMap<>();
    @ElementCollection
    @JoinColumn
    private Map<Object, Object> shelfAssignedToItem = new HashMap<>();
    @ElementCollection
    @JoinColumn
    private Map<Object, Double> inboundItem = new HashMap<>();
    private String inventoryManagement;
    private String personInCharge;




    //default constructor
    public Warehouse(){

    }


    //actual constructor
    public Warehouse(Long id, String name, Double quantity, List<Object> storedItems, LocalDateTime createdAt, String status, Map<Object, Boolean> itemShipped, Map<Object, Object> shelfAssignedToItem, Map<Object, Double> inboundItem, String inventoryManagement, String personInCharge){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.storedItems = storedItems;
        this.createdAt = createdAt;
        this.status = status;
        this.itemShipped = itemShipped;
        this.shelfAssignedToItem = shelfAssignedToItem;
        this.inboundItem = inboundItem;
        this.inventoryManagement = inventoryManagement;
        this.personInCharge = personInCharge;
    }

    //getters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Double getQuantity(){
        return this.quantity;
    }

    public List<Object> getStoredItems(){
        return this.storedItems;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public String getStatus(){
        return this.status;
    }

    public Map<Object, Boolean> getItemsShipped(){
        return this.itemShipped;
    }

    public Map<Object, Object> getShelfAssignedToItem(){
        return this.shelfAssignedToItem;
    }


    public Map<Object, Double> getInboundItem(){
        return this.inboundItem;
    }

    public String getInventoryManagement(){
        return this.inventoryManagement;
    }

    public String getPersonInCharge(){
        return this.personInCharge;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(Double quantity){
        this.quantity = quantity;
    }

    public void setStoredItems(List<Object> storedItems){
        this.storedItems = storedItems;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setItemShipped(Map<Object, Boolean> itemShipped){
        this.itemShipped = itemShipped;
    }


    public void setShelfAssignedToItem(Map<Object, Object> shelfAssignedToItem){
        this.shelfAssignedToItem = shelfAssignedToItem;
    }

    public void setInboundItem(Map<Object, Double> inboundItem){
        this.inboundItem = inboundItem;
    }


    public void setInventoryManagement(String inventoryManagement){
        this.inventoryManagement = inventoryManagement;
    }

    public void setPersonInCharge(String personInCharge){
        this.personInCharge = personInCharge;
    }

    public String toString(){
        return "ID: " + this.id + '\n' + "Warehouse Name: " + this.name + '\n' + "Warehouse Total Quantity Stored: " + this.quantity + '\n' + "Stored Items: " + this.storedItems + '\n' + "Created At: " + this.createdAt + '\n' + "Warehouse Status: " + this.status + '\n' + "Item(s) Shipped: " + this.itemShipped + '\n' + "Shelf Assigned To Item: " + this.shelfAssignedToItem + '\n' + "Inbound Items: " + this.inboundItem + '\n' + "Inventory Management: " + this.inventoryManagement + '\n' + "Person-In-Charge: " + this.personInCharge;
    }
}
