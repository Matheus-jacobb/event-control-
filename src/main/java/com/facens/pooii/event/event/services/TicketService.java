package com.facens.pooii.event.event.services;

import java.time.Instant;
import java.util.List;

import com.facens.pooii.event.event.DTO.TicketInsertDTO;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Ticket;
import com.facens.pooii.event.event.repositories.EventRepository;
import com.facens.pooii.event.event.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    public List<Ticket> getTicketByEvent(Long idEvent) {
        Event event = eventService.getEventById(idEvent);
        return event.getTickets();
    }

    public Ticket insertTicket(Long idEvent, TicketInsertDTO dto) {
        Event event = eventService.getEventById(idEvent);
        Ticket ticket = new Ticket(dto);
        ticket.setDate(Instant.now());
        ticket = ticketRepository.save(ticket);
        event.addTicket(ticket);
        event = eventRepository.save(event);
        return ticket;
    }

}
