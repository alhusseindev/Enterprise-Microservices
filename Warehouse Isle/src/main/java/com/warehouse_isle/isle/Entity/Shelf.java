package com.warehouse_isle.isle.Entity;

import javax.persistence.*;
import java.util.List;


@Table
@Entity
public class Shelf {
    @Id @GeneratedValue
    public Long id;
    public String shelfName;
    @ElementCollection
    @JoinColumn
    public List<Object> itemsOnShelf;


    public Shelf(){

    }

    public Shelf(Long id, String shelfName, List<Object> itemsOnShelf){
        this.id = id;
        this.shelfName = shelfName;
        this.itemsOnShelf = itemsOnShelf;
    }

    //getters
    public Long getId(){
        return this.id;
    }

    public String getShelfName(){
        return this.shelfName;
    }

    public List<Object> getItemsOnShelf(){
        return this.itemsOnShelf;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setShelfName(String shelfName){
        this.shelfName = shelfName;
    }

    public void setItemsOnShelf(List<Object> itemsOnShelf){
        this.itemsOnShelf = itemsOnShelf;
    }

    public String toString(){
        return "ID: " + this.id + '\n' + "Shelf Name: " + this.shelfName + '\n' + "Items on Shelf: " + this.itemsOnShelf + '\n';
    }
}
