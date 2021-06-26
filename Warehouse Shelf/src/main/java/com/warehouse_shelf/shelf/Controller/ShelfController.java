package com.warehouse_shelf.shelf.Controller;

import com.warehouse_shelf.shelf.Entity.Shelf;
import com.warehouse_shelf.shelf.Exceptions.ShelfException;
import com.warehouse_shelf.shelf.Repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/shelves/shelf")
@RestController
public class ShelfController {

    @Autowired
    private static ShelfRepository myShelfRepository;

    @GetMapping("/list")
    public List<Shelf> listShelves(){
        return myShelfRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Shelf getShelfById(@PathVariable Long id) throws ShelfException{
        return myShelfRepository.findById(id).orElseThrow(() -> new ShelfException("Shelf Not Found!"));
    }

    @PostMapping("/new")
    public Shelf createShelf(@RequestBody Shelf myShelf){
        return myShelfRepository.save(myShelf);
    }

    @PatchMapping("/update/{id}")
    public Shelf updateShelf(@RequestBody Shelf myShelf, @PathVariable Long id){
        return myShelfRepository.findById(id).map((shelf) ->{
            shelf.setId(myShelf.getId());
            shelf.setShelfName(myShelf.getShelfName());
            shelf.setItemsOnShelf(myShelf.getItemsOnShelf());
            return myShelfRepository.save(shelf);
        }).orElseGet(() ->{
           myShelf.setId(id);
           return myShelfRepository.save(myShelf);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShelf(@PathVariable Long id){
        myShelfRepository.deleteById(id);
    }


}
