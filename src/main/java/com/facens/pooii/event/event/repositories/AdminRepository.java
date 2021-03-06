package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Page<Admin> findAll(Pageable pageRequest);
}
