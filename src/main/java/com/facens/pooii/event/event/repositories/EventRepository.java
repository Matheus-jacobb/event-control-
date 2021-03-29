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
    @Query(" SELECT e FROM Event e " +
           " WHERE " + 
           " LOWER(e.name)  LIKE LOWER(CONCAT('%', :name,  '%')) AND " +
           " LOWER(e.place) LIKE LOWER(CONCAT('%', :place, '%')) "
    )

    public Page <Event> find(Pageable pageRequest, String name, String place); 

}
