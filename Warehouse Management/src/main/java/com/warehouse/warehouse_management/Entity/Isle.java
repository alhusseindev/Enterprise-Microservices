package com.warehouse.warehouse_management.Entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Isle {
    @Id @GeneratedValue
    public Long id;
    public String name;
    @OneToMany
    @JoinColumn
    public List<Shelf> isleShelf;

    //constructor
    public Isle(){

    }

    public Isle(Long id, String name, List<Shelf> isleShelf){
        this.id = id;
        this.name = name;
        this.isleShelf = isleShelf;
    }

    //getters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<Shelf> getIsleShelf(){
        return this.isleShelf;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIsleShelf(List<Shelf> isleShelf){
        this.isleShelf = isleShelf;
    }

    public String tostring(){
        return "ISLE ID: " + this.id + '\n' + "ISLE Name: " + this.name + '\n' + "Shelf: " + this.isleShelf + '\n';
    }
}
