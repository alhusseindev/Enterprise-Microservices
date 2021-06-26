package com.warehouse_shelf.shelf.Repository;

import com.warehouse_shelf.shelf.Entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

}
