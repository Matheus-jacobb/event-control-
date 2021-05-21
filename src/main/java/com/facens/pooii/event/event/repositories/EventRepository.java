package com.facens.pooii.event.event.repositories;

import java.time.LocalDate;

import com.facens.pooii.event.event.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    //Consulta JPQL
    @Query(" SELECT c FROM Event c " +
           " WHERE " + 
           " LOWER(c.name)          LIKE LOWER(CONCAT('%', :name,  '%')) AND " +
           " c.startDate            >=      :start_date AND " + 
           " LOWER(c.description)   LIKE LOWER(CONCAT('%', :description, '%')) "
    )

    public Page <Event> find(Pageable pageRequest, String name, LocalDate start_date, String description); 

}
