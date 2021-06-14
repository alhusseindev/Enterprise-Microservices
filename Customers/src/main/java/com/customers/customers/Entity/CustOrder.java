package com.customers.customers.Entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Table
@Entity
public class CustOrder {
    @Id
    @GeneratedValue
    private Long orderNumber;
    @CreatedDate
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    @LastModifiedDate
    private LocalDate orderDate = LocalDate.now();
    @DateTimeFormat(pattern= "hh:mm:ss")
    private LocalTime orderTime = LocalTime.now();
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    //Products
    private List<Product> orderItems = new ArrayList<>();
    //Components
    @OneToMany(cascade= {CascadeType.ALL})
    @JoinColumn
    private List<ProductComponent> orderItemComponents = new ArrayList<>();
    private String paymentMethod;
    private Double orderQuantity = 0.0;
    private Double tax = 0.0;
    private Double miscellaneous = 0.0;
    private Double totalCharge = 0.0;
    private String orderStatus;


    //default constructor
    public CustOrder(){

    }

    public CustOrder(Long orderNumber, LocalDate orderDate, LocalTime orderTime, List<Product> orderItems, List<ProductComponent> orderItemComponents, String paymentMethod, Double totalCharge, Double orderQuantity, Double tax, Double miscellaneous, String orderStatus){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
        this.orderItemComponents = orderItemComponents;
        this.paymentMethod = paymentMethod;
        this.totalCharge = totalCharge;
        this.orderQuantity = orderQuantity;
        this.tax = tax;
        this.miscellaneous = miscellaneous;
        this.orderStatus = orderStatus;
    }

    //getters
    public Long getOrderNumber(){
        return this.orderNumber;
    }

    public LocalDate getOrderDate(){
        return this.orderDate;
    }

    public List<Product> getOrderItems(){
        return this.orderItems;
    }

    public List<ProductComponent> getOrderItemComponents(){
        return this.orderItemComponents;
    }


    public String getPaymentMethod(){
        return this.paymentMethod;
    }

    public Double getTax(){
        return this.tax;
    }

    public Double getMiscellaneous(){
        return this.miscellaneous;
    }

    public Double getOrderQuantity(){
        return this.orderQuantity;
    }

    public Double getTotalCharge(){
        return this.totalCharge;
    }

    public String getOrderStatus(){
        return this.orderStatus;
    }


    public Double calculateTotalCharge(){
        for(Product myProduct : orderItems){
            this.totalCharge = this.totalCharge + (myProduct.getProductPrice() * this.orderQuantity);
            this.totalCharge = this.totalCharge + (this.tax * this.totalCharge);
            double productStock = myProduct.getQuantity();
            productStock = productStock - this.orderQuantity;
        }
        return this.totalCharge;
    }



    public LocalTime getOrderTime(){
        return this.orderTime;
    }


    //setters
    public void setOrderNumber(Long orderNumber){
        this.orderNumber = orderNumber;
    }

    public void setOrderDate(LocalDate orderDate){
        this.orderDate = orderDate;
    }

    public void setOrderItems(List<Product> orderItems){
        this.orderItems = orderItems;
    }

    public void addOrderItem(Product myProduct){
        this.orderItems.add(myProduct);
    }

    public void removeOrderItem(Product myProduct){
        this.orderItems.remove(myProduct);
    }



    public void setOrderItemComponents(List<ProductComponent> orderItemComponents){
        this.orderItemComponents = orderItemComponents;
    }

    public void addOrderItemComponent(ProductComponent myProductComponent){
        this.orderItemComponents.add(myProductComponent);
    }

    public void removeOrderItemComponent(ProductComponent myProductComponent){
        this.orderItemComponents.remove(myProductComponent);
    }



    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }


    public void setOrderQuantity(Double orderQuantity){
        this.orderQuantity = orderQuantity;
    }

    public void setTotalCharge(Double totalCharge){
        this.totalCharge = totalCharge;
    }

    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }

    public void setTax(Double tax){
        this.tax = tax;
    }

    public void setMiscellaneous(Double miscellaneous){
        this.miscellaneous = miscellaneous;
    }

    public void setOrderTime(LocalTime orderTime){
        this.orderTime = orderTime;
    }


    //toString() method
    public String toString(){
        return "Order\n" + "Order Number: " + this.orderNumber + "\n" + "Order Status" + this.orderStatus + "\n" + "Order Date: " + this.orderDate + "\n" + "Order Time: " + this.orderTime + "\n" + "Order Items: " + this.orderItems + "\n" + "Miscellaneous: " + this.miscellaneous + '\n' + "Taxes: " + this.tax + '\n' + "Payment Method: " + this.paymentMethod + "\n" + "Total Charge: " + this.totalCharge + "\n" + "Order Status: " + this.orderStatus;
    }


}