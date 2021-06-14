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



    /**
    @PostMapping("/components/add/product:{productId}/component:{componentId}")
    public void addComponentToProductComponents(@PathVariable Long productId, @PathVariable Long componentId) throws ProductException{
        //Product myaProduct = new Product();

        //selecting product by ID
        //mapping
        myProductRepository.findById(productId).map((myProduct) ->{
            myProduct.addProductComponent(componentId);
            return myProductRepository.save(myProduct);
        });


        //myaProduct.addProductComponent(componentId);
    }

     */

    /**
    @DeleteMapping("/components/remove/product:{productId}/component:{componentId}")
    public void deleteComponentFromProductComponents(@PathVariable Long productId, @PathVariable Long componentId){
        myProductRepository.findById(productId).map((myProduct) ->{
           myProduct.removeProductComponent(componentId);
           return myProductRepository.save(myProduct);
        });
    }

    */


    /* */

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product myProduct, @PathVariable Long id){
        return myProductRepository.findById(id).map((product) ->{
            product.setName(myProduct.getName());
            product.setProductComponents(myProduct.getProductComponents());
            product.setProductCost(myProduct.getProductCost());
            product.setProductPrice(myProduct.getProductPrice());
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
    public void deleteProduct(@PathVariable Long id) throws ProductException{
        myProductRepository.deleteById(id);
        throw new ProductException("Product Deletion Error!");
    }

    @PostMapping("/component/list/add/{id}")
    public void addProductComponent(@PathVariable Long id){
        ProductComponent myProductComponentMono =
                    myWebClient
                            .get()
                            .uri("/components/component/get/{"+ id +'}')
                            .retrieve()
                            .bodyToMono(ProductComponent.class).block();
        myProduct.addProductComponent(myProductComponentMono);
    }

    @DeleteMapping("/component/remove/{id}")
    public void removeProductComponent(@PathVariable Long id){
        ProductComponent myProductComponentMono =
                myWebClient
                .delete()
                .uri("/components/component/delete/{" + id + '}')
                .retrieve()
                .bodyToMono(ProductComponent.class).block();

        myProduct.removeProductComponent(myProductComponentMono);
    }


    @GetMapping("/product/total/count")
    public Long getProductCount(){
        return myProductRepository.count();
    }


    @GetMapping("/components/get/all")
    public Flux<ProductComponent> getProductComponents() throws ProductException {
        //Product myProduct = new Product();
        Flux<ProductComponent> myProductComponentFlux =
                    myWebClient
                    .get()
                    .uri("/components/component/list")
                    .retrieve()
                    .bodyToFlux(ProductComponent.class);
        //myProduct.setProductComponents(myProductComponentFlux);

        return myProductComponentFlux;
    }


    //getting only the component with the following ID
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

}


