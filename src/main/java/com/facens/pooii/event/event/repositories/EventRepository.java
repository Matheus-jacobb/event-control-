package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
    
    //Consulta JPQL
    @Query("SELECT c FROM Event c" +
            "WHERE c.name LIKE CONCAT('%', :name, '%')"        
    )
    public Page <Event> find(Pageable pageRequest, String name, String place); 

}
