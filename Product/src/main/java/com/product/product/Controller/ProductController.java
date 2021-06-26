package com.product.product.Controller;


import com.product.product.Entity.Product;
import com.product.product.Entity.ProductComponent;
import com.product.product.Exceptions.ProductException;
import com.product.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/products/product")
public class ProductController {

    @Autowired
    private ProductRepository myProductRepository;

    Product myProduct = new Product();
    
    // Making a call to an external API
    WebClient myWebClient = WebClient.create("http://localhost:8080/api");


    @GetMapping("/list")
    public List<Product> listProducts(){
        return myProductRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductException {
        return myProductRepository.findById(id).orElseThrow(() -> new ProductException("Product Not Found!"));
    }

    @PostMapping("/new")
    public Product createNewProduct(@RequestBody Product myProduct){
        return myProductRepository.save(myProduct);
    }


    /* */

    @PatchMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product myProduct, @PathVariable Long id){
        return myProductRepository.findById(id).map((product) ->{
            product.setName(myProduct.getName());
            product.setProductComponents(myProduct.getProductComponents());
            product.setProductCost(myProduct.getProductCost());
            product.setProductPrice(myProduct.getProductPrice());
            product.setProductUnits(myProduct.getProductUnits());
            product.setQuantity(myProduct.getQuantity());
            product.setCreatedAt(myProduct.getCreatedAt());
            product.setStatus(myProduct.getStatus());
            return myProductRepository.save(product);
        }).orElseGet(() ->{
            myProduct.setId(id);
            return myProductRepository.save(myProduct);
        });

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        myProductRepository.deleteById(id);
    }

    @PostMapping("/component/add/{productComponentId}")
    public void addProductComponent(@PathVariable Long productComponentId) throws ProductException{
            ProductComponent myProductComponentMono =
                    myWebClient
                            .get()
                            .uri("/components/component/get/{" + productComponentId + "}")
                            .retrieve()
                            .bodyToMono(ProductComponent.class).block();
            myProduct.addProductComponent(myProductComponentMono);

    }

    @DeleteMapping("/component/remove/{productComponentId}")
    public void removeProductComponent(@PathVariable Long productComponentId) throws ProductException{
            ProductComponent myProductComponentMono =
                    myWebClient
                            .delete()
                            .uri("/components/component/get/{" + productComponentId + "}")
                            .retrieve()
                            .bodyToMono(ProductComponent.class).block();

            myProduct.removeProductComponent(myProductComponentMono);

    }


    @GetMapping("/product/total/count")
    public Long getProductCount(){
        return myProductRepository.count();
    }


    @GetMapping("/components/get/all")
    public Flux<ProductComponent> getAllProductComponents() throws ProductException {
            Flux<ProductComponent> myProductComponentFlux =
                    myWebClient
                            .get()
                            .uri("/components/component/list")
                            .retrieve()
                            .bodyToFlux(ProductComponent.class);
            //myProduct.setProductComponents(myProductComponentFlux);
            return myProductComponentFlux;

    }


    @GetMapping("/components/get/{productComponentId}")
    public Mono<ProductComponent> getSingleProductComponent(@PathVariable Long productComponentId) throws ProductException{
            Mono<ProductComponent> myProductComponentMono =
                    myWebClient
                            .get()
                            .uri("/components/component/get/{" + productComponentId + "}")
                            .retrieve()
                            .bodyToMono(ProductComponent.class);

            return myProductComponentMono;
    }


    //getting only the component with the following ID
    /**
    @GetMapping("/components/getbyid/")
    public Mono<ProductComponent> getSingleProductComponent() throws ProductException{
        List<ProductComponent> compList = myProduct.getProductComponents();
        Mono<ProductComponent> myProductComponentMono = null;
        for(ProductComponent i : compList) {
            myProductComponentMono =
                    myWebClient
                            .get()
                            .uri("/components/component/get/{"+ i.getId() +'}')
                            .retrieve()
                            .bodyToMono(ProductComponent.class);
        }

        return myProductComponentMono;
    }

    */





}


