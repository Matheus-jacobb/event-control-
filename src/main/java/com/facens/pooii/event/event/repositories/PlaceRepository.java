package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    public Page<Place> findAll(Pageable pegeRequest);
}
