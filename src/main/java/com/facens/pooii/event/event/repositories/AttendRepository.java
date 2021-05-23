package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Attend;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long> {

    public Page <Attend> findAll(Pageable pageRequest); 
}