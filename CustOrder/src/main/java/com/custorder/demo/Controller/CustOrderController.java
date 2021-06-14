package com.custorder.demo.Controller;


import com.custorder.demo.Entity.CustOrder;
import com.custorder.demo.Entity.Product;
import com.custorder.demo.Entity.ProductComponent;
import com.custorder.demo.Exceptions.CustOrderException;
import com.custorder.demo.Repository.CustOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/orders/order")
@RestController
public class CustOrderController {
    @Autowired
    private CustOrderRepository myCustOrderRepository;

    CustOrder myCustOrder = new CustOrder();

    @GetMapping("/list")
    public List<CustOrder> listOrders(){
        return myCustOrderRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public CustOrder findOrderById(@PathVariable Long id) throws CustOrderException {
        return myCustOrderRepository.findById(id).orElseThrow(() -> new CustOrderException("Order Not Found!"));
    }

    @PostMapping("/new")
    public CustOrder createOrder(@RequestBody CustOrder myOrder){
        return myCustOrderRepository.save(myOrder);
    }

    @PutMapping("/update/{id}")
    public CustOrder updateOrder(@RequestBody CustOrder myOrder, @PathVariable Long id){
        return myCustOrderRepository.findById(id).map((order) ->{
            order.setOrderDate(myOrder.getOrderDate());
            order.setOrderItems(myOrder.getOrderItems());
            order.setOrderItemComponents(myOrder.getOrderItemComponents());
            order.setOrderStatus(myOrder.getOrderStatus());
            order.setOrderTime(myOrder.getOrderTime());
            order.setOrderQuantity(myOrder.getOrderQuantity());
            order.setPaymentMethod(myOrder.getPaymentMethod());
            order.setTotalCharge(myOrder.getTotalCharge());
            order.setMiscellaneous(myOrder.getMiscellaneous());
            order.setTax(myOrder.getTax());
            return myCustOrderRepository.save(order);
        }).orElseGet(() ->{
            myOrder.setOrderNumber(id);
            return myCustOrderRepository.save(myOrder);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        myCustOrderRepository.deleteById(id);
    }


    /** For Finished Products  */
    @PostMapping("/items/add")
    public void addItemsToOrder(@RequestBody Product myProduct){
        myCustOrder.addOrderItem(myProduct);
    }

    // test this
    @DeleteMapping("/items/remove/{id}")
    public void removeItemsFromOrder(@PathVariable Long id, @RequestBody Product myProduct) throws CustOrderException{
        //here id is for selecting the order by id
        CustOrder myCustOrder = myCustOrderRepository.findById(id).orElseThrow(() -> new CustOrderException("Error Removing Item From Order!"));
        myCustOrder.removeOrderItem(myProduct);

    }

    /** For Components (Ingredients) */

    @PostMapping("/items/add")
    public void addItemsToOrderComponents(@RequestBody ProductComponent myProductComponent){
        myCustOrder.addOrderItemComponent(myProductComponent);
    }

    // test this
    @DeleteMapping("/items/remove/{id}")
    public void removeItemsFromOrderComponents(@PathVariable Long id, @RequestBody Product myProduct) throws CustOrderException{
        //here id is for selecting the order by id
        CustOrder myCustOrder = myCustOrderRepository.findById(id).orElseThrow(() -> new CustOrderException("Error Removing Items from Order Components!"));
        myCustOrder.removeOrderItem(myProduct);
    }


    @PostMapping("/total-charge/calculate/{id}")
    public Double calculateTotalCharge(@PathVariable Long id) throws CustOrderException{
        CustOrder myCustOrder =  myCustOrderRepository.findById(id).orElseThrow(() -> new CustOrderException("Error Calculating Total Charge!"));
        return myCustOrder.calculateTotalCharge();
    }




}