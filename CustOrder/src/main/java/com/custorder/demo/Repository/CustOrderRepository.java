package com.custorder.demo.Repository;

import com.custorder.demo.Entity.CustOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustOrderRepository extends JpaRepository<CustOrder, Long> {

}
