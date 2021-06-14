package com.accounting.accounting.Entity;



/** ************  Financial Statement Format/Class ************* */

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Table
public class Accounting {
    @Id @GeneratedValue
    @CreatedBy @LastModifiedBy
    private Long id;

    //product
    @OneToMany
    @JoinColumn
    private List<Product> productList;

    //component
    @OneToMany
    @JoinColumn
    private List<ProductComponent> componentList;

    //CustOrder
    @OneToMany
    @JoinColumn
    private List<CustOrder> custOrderList;

    //Customer
    @OneToMany
    @JoinColumn
    private List<Customer> customerList;

    //Expenses
    @OneToMany
    @JoinColumn
    private List<Expenses> expenseList;

    //Suppliers
    @OneToMany
    @JoinColumn
    private List<Supplier> supplierList;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Double productCogs = 0.0;
    private Double ownersEquity = 0.0;
    private Double profit = 0.0;
    private Double loss = 0.0;

    //default constructor
    public Accounting(){

    }

    public Accounting(Long id, List<Product> productList, List<ProductComponent> componentList, List<CustOrder> custOrderList, List<Customer> customerList, List<Expenses> expenseList, List<Supplier> supplierList, LocalDateTime createdAt, Double productCogs, Double profit, Double loss, Double ownersEquity){
        this.id = id;
        this.productList = productList;
        this.componentList = componentList;
        this.customerList = customerList;
        this.custOrderList = custOrderList;
        this.expenseList = expenseList;
        this.supplierList = supplierList;
        this.createdAt = createdAt;
        this.productCogs = productCogs;
        this.profit = profit;
        this.loss = loss;
        this.ownersEquity = ownersEquity;
    }


    //Getters
    public Long getId(){
        return this.id;
    }

    public List<Product> getProductList(){
        return this.productList;
    }

    public List<ProductComponent> getComponentList(){
        return this.componentList;
    }

    public List<Customer> getCustomerList(){
        return this.customerList;
    }

    public List<CustOrder> getCustOrderList(){
        return this.custOrderList;
    }

    public List<Supplier> getSupplierList(){
        return this.supplierList;
    }

    public List<Expenses> getExpenseList(){
        return this.expenseList;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public Double getProductCogs(){
        return this.productCogs;
    }

    public Double getProfit(){
        return this.profit;
    }

    public Double getLoss(){
        return this.loss;
    }

    public Double getOwnersEquity(){
        return this.ownersEquity;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
    }

    public void setComponentList(List<ProductComponent> componentList){
        this.componentList = componentList;
    }

    public void setCustOrderList(List<CustOrder> custOrderList){
        this.custOrderList = custOrderList;
    }

    public void setCustomerList(List<Customer> customerList){
        this.customerList = customerList;
    }

    public void setExpenseList(List<Expenses> expenseList){
        this.expenseList = expenseList;
    }

    public void setSupplierList(List<Supplier> supplierList){
        this.supplierList = supplierList;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setProductCogs(Double productCogs){
        this.productCogs = productCogs;
    }

    public void setProfit(Double profit){
        this.profit = profit;
    }

    public void setLoss(Double loss){
        this.loss = loss;
    }

    public void setOwnersEquity(Double ownersEquity){
        this.ownersEquity = ownersEquity;
    }



    public Double calculateTotalProfitOfAllProducts(){
        List<Product> myCustOrderList;
        double taxes = 0.0;
        double expenseValue = 0.0;
        double expPlusTax = 0.0;
        double finalValue;
        for(Expenses myExpenseObj : expenseList){
            expenseValue = expenseValue + myExpenseObj.getExpenseValue();
        }

        for(CustOrder myCustOrder : custOrderList){
            myCustOrderList = myCustOrder.getOrderItems();
            for(Product myProduct : myCustOrderList){
                this.productCogs = myProduct.calculateProductMarginValue();
            }

            taxes = taxes + myCustOrder.getTax();
        }

        expPlusTax = taxes + expenseValue;

        finalValue = this.productCogs - expPlusTax;

        return finalValue;
    }



    public String toString(){
        return "ID: " + this.id + '\n' + "Customers: \n" + this.customerList + '\n' + "Customer Order(s): \n" + this.custOrderList + '\n' + "Products: \n" + this.productList + '\n' + "Components: \n" + this.componentList + '\n' + "Suppliers: \n" + this.supplierList + '\n' + "Product COGS: \n" + this.productCogs + '\n' + "Profit: \n" + this.profit + '\n' + "Loss: \n" + this.loss + '\n';
    }

}
