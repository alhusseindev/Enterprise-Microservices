package com.component.component.Controller;

import com.component.component.Entity.Component;
import com.component.component.Exceptions.ComponentException;
import com.component.component.Repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/components/component")
public class ComponentController{
    @Autowired
    private ComponentRepository myComponentRepository;


    @GetMapping("/list")
    public List<Component> listComponents() throws Exception{
        try {
            return myComponentRepository.findAll();
        }catch(Exception Ex){
            throw new ComponentException("Component Error Exception Occurred!");
        }
    }

    @GetMapping("/get/{id}")
    public Component findComponentById(@PathVariable Long id) throws ComponentException {
        return myComponentRepository.findById(id).orElseThrow(() -> new ComponentException("Component Not Found!"));
    }

    @PostMapping("/new")
    public Component createComponent(@RequestBody Component myComponent){
        return myComponentRepository.save(myComponent);
    }


    @PatchMapping("/update/{id}")
    public Component updateComponent(@RequestBody Component myComponent, @PathVariable Long id){
        return myComponentRepository.findById(id).map((component) ->{
            component.setName(myComponent.getname());
            component.setUnit(myComponent.getUnit());
            component.setQuantity(myComponent.getQuantity());
            component.setComponentCost(myComponent.getComponentCost());
            component.setComponentPrice(myComponent.getComponentPrice());
            component.setCreatedAt(myComponent.getCreatedAt());
            component.setStatus(myComponent.getStatus());
            return myComponentRepository.save(component);
        }).orElseGet(() ->{
            myComponent.setId(id);
            return myComponentRepository.save(myComponent);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComponent(@PathVariable Long id){
        myComponentRepository.deleteById(id);
    }



    @GetMapping("/component/total/count")
    public Long getComponentCount(){
        return myComponentRepository.count();
    }
}