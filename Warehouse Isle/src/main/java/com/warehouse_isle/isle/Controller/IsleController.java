package com.warehouse_isle.isle.Controller;

import com.warehouse_isle.isle.Entity.Isle;
import com.warehouse_isle.isle.Entity.Shelf;
import com.warehouse_isle.isle.Exceptions.IsleException;
import com.warehouse_isle.isle.Repository.IsleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/isles/isle")
@RestController
public class IsleController {

    @Autowired
    private static IsleRepository myIsleRepository;


    WebClient myWebClient = WebClient.create("http://localhost:8080/api");

    @GetMapping("/list")
    public List<Isle> listIsles(){
        return myIsleRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Isle getIsleById(@PathVariable Long id) throws IsleException{
        return myIsleRepository.findById(id).orElseThrow(() -> new IsleException("Isle Not Found!"));
    }

    @PostMapping("/new")
    public Isle createIsle(@RequestBody Isle myIsle){
        return myIsleRepository.save(myIsle);
    }

    @PatchMapping("/update/{id}")
    public Isle updateIsle(@RequestBody Isle myIsle , @PathVariable Long id){
        return myIsleRepository.findById(id).map((isle) ->{
            isle.setName(myIsle.getName());
            isle.setIsleShelf(myIsle.getIsleShelf());
            return myIsleRepository.save(isle);
        }).orElseGet(() ->{
            myIsle.setId(id);
            return myIsleRepository.save(myIsle);
        });
    }



    @DeleteMapping("/delete/{id}")
    public void deleteIsleById(@PathVariable Long id){
        myIsleRepository.deleteById(id);
    }


    @GetMapping("/shelves/list")
    public Flux<Shelf> listShelves(){
        Flux<Shelf> getShelves = myWebClient
                .get()
                .uri("/shelves/shelf/list")
                .retrieve()
                .bodyToFlux(Shelf.class);
        return getShelves;
    }
}
