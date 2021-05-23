package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Attend;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttendRepository extends JpaRepository<Attend, Long> {

    @Query(" SELECT c FROM Attend c ")

    public Page <Attend> find(Pageable pageRequest, String name, String email); 
}
