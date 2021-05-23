package com.facens.pooii.event.event.repositories;

import com.facens.pooii.event.event.entities.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}