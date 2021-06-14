package com.component.component.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.component.component.Entity.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
}
