package com.warehouse_isle.isle.Repository;

import com.warehouse_isle.isle.Entity.Isle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsleRepository extends JpaRepository<Isle, Long> {
}
