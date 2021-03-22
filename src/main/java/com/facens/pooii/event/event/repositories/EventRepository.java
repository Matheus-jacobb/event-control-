package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
    
}
