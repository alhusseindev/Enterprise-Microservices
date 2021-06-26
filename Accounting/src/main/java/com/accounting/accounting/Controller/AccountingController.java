package com.accounting.accounting.Controller;



import com.accounting.accounting.Entity.*;
import com.accounting.accounting.Exception.AccountingException;
import com.accounting.accounting.Repository.AccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;



@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/accounting/accounts")
@RestController
public class AccountingController{

    @Autowired
    private AccountingRepository myAccountingRepository;


    Accounting myAccounting = new Accounting();


    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

    @GetMapping("/list")
    public List<Accounting> listAccounting(){
        return myAccountingRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Accounting getAccountingById(@PathVariable Long id) throws AccountingException {
        return myAccountingRepository.findById(id).orElseThrow(() -> new AccountingException("Invalid Accounting ID!"));
    }

    @PostMapping("/new")
    public Accounting createNewAccounting(@RequestBody Accounting accounting){
        return myAccountingRepository.save(accounting);
    }

    @PatchMapping("/update/{id}")
    public Accounting updateAccounting(@RequestBody Accounting myAccounting, @PathVariable Long id){
        return myAccountingRepository.findById(id).map((accounting) ->{
            accounting.setCustomerList(myAccounting.getCustomerList());
            accounting.setComponentList(myAccounting.getComponentList());
            accounting.setCustOrderList(myAccounting.getCustOrderList());
            accounting.setProductList(myAccounting.getProductList());
            accounting.setExpenseList(myAccounting.getExpenseList());
            accounting.setSupplierList(myAccounting.getSupplierList());
            accounting.setCreatedAt(myAccounting.getCreatedAt());
            accounting.setProductCogs(myAccounting.getProductCogs());
            accounting.setProfit(myAccounting.getProfit());
            accounting.setLoss(myAccounting.getLoss());
            accounting.setOwnersEquity(myAccounting.getOwnersEquity());
            return myAccountingRepository.save(accounting);
        }).orElseGet(() ->{
            myAccounting.setId(id);
            return myAccountingRepository.save(myAccounting);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        myAccountingRepository.deleteById(id);
    }



    /* business logic  methods */
    @GetMapping("/customer-orders/get/all")
    public Flux<CustOrder> getCustOrders(){
        Flux<CustOrder> allFetchedCustOrders = myWebClient
                .get()
                .uri("/orders/order/list")
                .retrieve()
                .bodyToFlux(CustOrder.class);

        return allFetchedCustOrders;
    }

    @GetMapping("/expenses/get/all")
    public Flux<Expenses> getAllExpenses(){
        Flux<Expenses> allFetchedExpenses = myWebClient
                .get()
                .uri("/expenses/expense/list")
                .retrieve()
                .bodyToFlux(Expenses.class);

        return allFetchedExpenses;
    }

   @GetMapping("/suppliers/get/all")
    public Flux<Supplier> getAllSuppliers(){
        Flux<Supplier> allFetchedSuppliers = myWebClient
                .get()
                .uri("/suppliers/supplier/list")
                .retrieve()
                .bodyToFlux(Supplier.class);
        return allFetchedSuppliers;
   }

   @GetMapping("/components/get/all")
    public Flux<ProductComponent> getAllProductComponents(){
        Flux<ProductComponent> allFetchedProductComponents = myWebClient
                .get()
                .uri("/components/component/list")
                .retrieve()
                .bodyToFlux(ProductComponent.class);
        return allFetchedProductComponents;
   }


   @GetMapping("/customers/get/all")
    public Flux<Customer> getAllCustomers(){
        Flux<Customer> allFetchedCustomers = myWebClient
                .get()
                .uri("customers/customer/list")
                .retrieve()
                .bodyToFlux(Customer.class);

        return allFetchedCustomers;
   }

   @GetMapping("/products/get/all")
    public Flux<Product> getAllProducts(){
        Flux<Product> allFetchedProducts = myWebClient
                .get()
                .uri("products/product/list")
                .retrieve()
                .bodyToFlux(Product.class);

        return allFetchedProducts;
   }


   





    /**
    @GetMapping("/products/profit")
    public Double calculateTotalGrossProfitOfAllProducts(){
        return myAccounting.calculateTotalProfitOfAllProducts();
    }

     */


}

