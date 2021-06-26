package com.custorder.demo.Controller;


import com.custorder.demo.Entity.CustOrder;
import com.custorder.demo.Entity.Product;
import com.custorder.demo.Entity.ProductComponent;
import com.custorder.demo.Exceptions.CustOrderException;
import com.custorder.demo.Repository.CustOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/orders/order")
@RestController
public class CustOrderController {
    @Autowired
    private CustOrderRepository myCustOrderRepository;

    CustOrder myCustOrder = new CustOrder();

    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

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

    @PatchMapping("/update/{id}")
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
    @PostMapping("/finished-product/items/add/{productID}")
    public void addProductItemsToOrder(@PathVariable Long productID){
        Product productFetched = myWebClient
                .get()
                .uri("/products/product/get/{" + productID + "}")
                .retrieve()
                .bodyToMono(Product.class).block();
        myCustOrder.addOrderItem(productFetched);
    }

    // test this
    @DeleteMapping("/finished-product/items/remove/{productID}")
    public void removeProductItemsFromOrder(@PathVariable Long productID) throws CustOrderException{
        //here id is for selecting the order by id
        Product productFetched = myWebClient
                .get()
                .uri("/products/product/get/{" + productID + "}")
                .retrieve()
                .bodyToMono(Product.class).block();
        myCustOrder.removeOrderItem(productFetched);
    }

    /** For Components (Ingredients) */

    @PostMapping("/items/add/{productComponentID}")
    public void addProductComponentItemToOrder(@PathVariable Long productComponentID){
        ProductComponent productComponentFetched = myWebClient
                .get()
                .uri("/components/component/get/{" + productComponentID + "}")
                .retrieve()
                .bodyToMono(ProductComponent.class).block();
        myCustOrder.addOrderItemComponent(productComponentFetched);
    }

    @DeleteMapping("/items/remove/{productComponentID}")
    public void removeProductComponentItemFromOrder(@PathVariable Long productComponentID) throws CustOrderException{
        //here id is for selecting the order by id
        ProductComponent productComponentFetched = myWebClient
                .get()
                .uri("/components/component/get/{" + productComponentID + "}")
                .retrieve()
                .bodyToMono(ProductComponent.class).block();
        myCustOrder.removeOrderItemComponent(productComponentFetched);
    }


    @PostMapping("/total-charge/calculate/{id}")
    public Double calculateTotalCharge(@PathVariable Long id) throws CustOrderException{
        CustOrder myCustOrder =  myCustOrderRepository.findById(id).orElseThrow(() -> new CustOrderException("Error Calculating Total Charge!"));
        return myCustOrder.calculateTotalCharge();
    }




}