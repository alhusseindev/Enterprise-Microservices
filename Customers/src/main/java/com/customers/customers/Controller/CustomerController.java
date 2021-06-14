package com.customers.customers.Controller;


import com.customers.customers.Entity.CustOrder;
import com.customers.customers.Entity.Customer;
import com.customers.customers.Exceptions.CustomerException;
import com.customers.customers.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/customers/customer")
@RestController
public class CustomerController{

    @Autowired
    private CustomerRepository myCustomerRepository;

    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

    @GetMapping("/list")
    public List<Customer> listCustomers(){
        return myCustomerRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable Long id) throws CustomerException{
        return myCustomerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer Not Found!"));
    }

    @PostMapping("/new")
    public Customer createCustomer(@RequestBody Customer myCustomer){
        return myCustomerRepository.save(myCustomer);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@RequestBody Customer myCustomer, @PathVariable Long id){
        return myCustomerRepository.findById(id).map((customer) ->{
            customer.setCustomerTitle(myCustomer.getCustomerTitle());
            customer.setCustomerFirstName(myCustomer.getCustomerFirstname());
            customer.setCustomerLastName(myCustomer.getCustomerLastName());
            customer.setCompanyName(myCustomer.getCompanyName());
            customer.setPhoneNumber(myCustomer.getPhoneNumber());
            customer.setOtherPhoneNumber(myCustomer.getOtherPhoneNumber());
            customer.setCustOrders(myCustomer.getCustOrders());
            customer.setAccountNumber(myCustomer.getAccountNumber());
            customer.setEmail(myCustomer.getEmail());
            customer.setAddress(myCustomer.getAddress());
            customer.setPaymentTerms(myCustomer.getPaymentTerms());
            customer.setNotes(myCustomer.getNotes());
            customer.setCreatedAt(myCustomer.getCreatedAt());
            //customer.setCustomerWorth(myCustomer.getCustomerWorth());
            return myCustomerRepository.save(customer);
        }).orElseGet(() ->{
            myCustomer.setId(id);
            return myCustomerRepository.save(myCustomer);
        });
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id){
        myCustomerRepository.deleteById(id);
    }



    @PostMapping("/order/add/customer:{customerID}/order:{customerOrderID}")
    public void addOrderToCustomerOrders(@PathVariable Long customerID , @PathVariable Long customerOrderID) throws CustomerException{
        // get order by order id, by making a request to the CustOrder api
        CustOrder selectedCustOrder =
                 myWebClient
                .get()
                .uri("/orders/order/get/{customerOrderID}")
                .retrieve()
                .bodyToMono(CustOrder.class).block();

        //find customer by id
        //then add the retrieved item from api to the orders.
        var CustomerSelected = myCustomerRepository.findById(customerID).orElseThrow(() -> new CustomerException("Error! Order Could Not be Added!"));
        CustomerSelected.addOrderToCustOrders(selectedCustOrder);
    }




    @GetMapping("/total/count")
    public Long getCustomerCount(){
        return myCustomerRepository.count();
    }

    @GetMapping("/orders/count/{customerID}")
    public Integer getTotalCustomerOrders(@PathVariable Long customerID) throws CustomerException{
        Customer customerSelected = myCustomerRepository.findById(customerID).orElseThrow(() -> new CustomerException("Error Getting Total Number of Customer Orders!"));
        Integer myCustTotalOrders = customerSelected.getTotalNoOfOrders();
        return myCustTotalOrders;
    }

    @GetMapping("/worth/{customerID}")
    public Double getCustomerTotalWorth(@PathVariable Long customerID) throws CustomerException{
        Customer customerSelected = myCustomerRepository.findById(customerID).orElseThrow(() -> new CustomerException("Error Getting Customer Total Worth!"));
        Double myCustTotalWorth = customerSelected.getCustomerWorth();
        return myCustTotalWorth;
    }

}