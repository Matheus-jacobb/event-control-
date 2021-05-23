package com.facens.pooii.event.event.services;

import java.util.List;

import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Ticket;
import com.facens.pooii.event.event.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EventService eventService;

    public List<Ticket> getTicketByEvent(Long idEvent){
        Event event = eventService.getEventById(idEvent);
        return event.getTickets();
    }

}
