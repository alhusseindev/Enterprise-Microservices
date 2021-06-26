package com.warehouse.warehouse_management.Controller;


import com.warehouse.warehouse_management.Entity.Isle;
import com.warehouse.warehouse_management.Entity.Shelf;
import com.warehouse.warehouse_management.Entity.Warehouse;
import com.warehouse.warehouse_management.Exceptions.WarehouseException;
import com.warehouse.warehouse_management.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/warehouses/warehouse")
@RestController
public class WarehouseController {

    @Autowired
    private static WarehouseRepository myWarehouseRepository;

    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

    @GetMapping("/list")
    public List<Warehouse> listWarehouses(){
        return myWarehouseRepository.findAll();
    }

    @GetMapping("get/{id}")
    public Warehouse getWarehouseById(@PathVariable Long id) throws WarehouseException{
        return myWarehouseRepository.findById(id).orElseThrow(() -> new WarehouseException("Error Finding Warehouse!"));
    }

    @PostMapping("/new")
    public Warehouse createWarehouse(@RequestBody Warehouse myWarehouse){
        return myWarehouseRepository.save(myWarehouse);
    }

    @PatchMapping("/update/{id}")
    public Warehouse updateWarehouse(@RequestBody Warehouse myWarehouse, @PathVariable Long id){
        return myWarehouseRepository.findById(id).map((warehouse) ->{
            warehouse.setName(myWarehouse.getName());
            warehouse.setQuantity(myWarehouse.getQuantity());
            warehouse.setStoredItems(myWarehouse.getStoredItems());
            warehouse.setCreatedAt(myWarehouse.getCreatedAt());
            warehouse.setItemShipped(myWarehouse.getItemsShipped());
            warehouse.setShelfAssignedToItem(myWarehouse.getShelfAssignedToItem());
            warehouse.setStatus(myWarehouse.getStatus());
            warehouse.setInboundItem(myWarehouse.getInboundItem());
            warehouse.setInventoryManagement(myWarehouse.getInventoryManagement());
            warehouse.setPersonInCharge(myWarehouse.getPersonInCharge());
            return myWarehouseRepository.save(warehouse);
        }).orElseGet(() ->{
            myWarehouse.setId(id);
            return myWarehouseRepository.save(myWarehouse);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWarehouseById(@PathVariable Long id){
        myWarehouseRepository.deleteById(id);
    }


    @GetMapping("/wh-isles/list")
    public Flux<Isle> listIsles(){
        Flux<Isle> getIsles = myWebClient
                .get()
                .uri("/isles/isle/list")
                .retrieve()
                .bodyToFlux(Isle.class);

        return getIsles;
    }

    @GetMapping("/wh-isles/get/{id}")
    public Mono<Isle> getIsleById(@PathVariable Long id){
        Mono<Isle> getSingleIsle = myWebClient
                .get()
                .uri("/isles/isle/get/{" + id + "}")
                .retrieve()
                .bodyToMono(Isle.class);

        return getSingleIsle;
    }


    @GetMapping("/wh-shelves/list")
    public Flux<Shelf> listShelves(){
        Flux<Shelf> getShelves = myWebClient
                .get()
                .uri("/shelves/shelf/list")
                .retrieve()
                .bodyToFlux(Shelf.class);

        return getShelves;
    }


    @GetMapping("/wh-shelves/get/{id}")
    public Mono<Shelf> getShelfById(@PathVariable Long id){
        Mono<Shelf> getSingleShelf = myWebClient
                .get()
                .uri("/shelves/shelf/get/{" + id + "}")
                .retrieve()
                .bodyToMono(Shelf.class);

        return getSingleShelf;
    }


}
