package com.supplier.supplier.Controller;

import com.supplier.supplier.Entity.Product;
import com.supplier.supplier.Entity.ProductComponent;
import com.supplier.supplier.Entity.Supplier;
import com.supplier.supplier.Exceptions.SupplierException;
import com.supplier.supplier.Repository.SupplierRepository;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/suppliers/supplier")
public class SupplierController{
    @Autowired
    private SupplierRepository mySupplierRepository;

    //Making a call to an external API
    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

    @GetMapping("/list")
    public List<Supplier> listSuppliers(){
        return mySupplierRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Supplier findSupplierById(@PathVariable Long id) throws SupplierException {
        return mySupplierRepository.findById(id).orElseThrow(() -> new SupplierException("Supplier Not Found!"));
    }

    @PostMapping("/new")
    public Supplier createSupplier(@RequestBody Supplier mySupplier){
        return mySupplierRepository.save(mySupplier);
    }

    @PatchMapping("/update/{id}")
    public Supplier updateSupplier(@RequestBody Supplier mySupplier, @PathVariable Long id){
        return mySupplierRepository.findById(id).map((supplier) ->{
            supplier.setSupplierTitle(mySupplier.getSupplierTitle());
            supplier.setSupplierFirstName(mySupplier.getSupplierFirstname());
            supplier.setSupplierLastName(mySupplier.getSupplierLastName());
            supplier.setCompanyName(mySupplier.getCompanyName());
            supplier.setPhoneNumber(mySupplier.getPhoneNumber());
            supplier.setOtherPhoneNumber(mySupplier.getOtherPhoneNumber());
            supplier.setAccountNumber(mySupplier.getAccountNumber());
            supplier.setEmail(mySupplier.getEmail());
            supplier.setAddress(mySupplier.getAddress());
            supplier.setWebsite(mySupplier.getWebsite());
            supplier.setHourlyRate(mySupplier.getHourlyRate());
            supplier.setTypeOfGoods(mySupplier.getTypeOfGoods());
            supplier.setPaymentTerms(mySupplier.getPaymentTerms());
            supplier.setNotes(mySupplier.getNotes());
            supplier.setItemsSupplied(mySupplier.getItemsSupplied());
            supplier.setCreatedAt(mySupplier.getCreatedAt());
            return mySupplierRepository.save(supplier);
        }).orElseGet(() ->{
            mySupplier.setId(id);
            return mySupplierRepository.save(mySupplier);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSupplier(@PathVariable Long id){
        mySupplierRepository.deleteById(id);
    }


    @GetMapping("/supplier/total/count")
    public Long getSupplierCount(){
        return mySupplierRepository.count();
    }

    @GetMapping("/products/get/all")
    public Flux<Product> getSupplierProducts() throws SupplierException{
        Flux<Product> myProductFlux =
                myWebClient
                .get()
                .uri("/products/product/list")
                .retrieve()
                .bodyToFlux(Product.class);
        return myProductFlux;
    }


    @GetMapping("/components/get/all")
    public Flux<ProductComponent> getSupplierComponents() throws SupplierException{
        Flux<ProductComponent> myComponentFlux =
                myWebClient
                .get()
                .uri("/components/component/list")
                .retrieve()
                .bodyToFlux(ProductComponent.class);
        return myComponentFlux;
    }




    /**
    public List<ProductComponent> getSupplierComponentById(Long id){

    }

     */
}