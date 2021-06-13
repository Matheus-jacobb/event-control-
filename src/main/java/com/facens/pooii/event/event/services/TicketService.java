package com.facens.pooii.event.event.services;

import java.time.Instant;
import java.util.List;

import com.facens.pooii.event.event.DTO.TicketInsertDTO;
import com.facens.pooii.event.event.entities.Attend;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Ticket;
import com.facens.pooii.event.event.entities.Type;
import com.facens.pooii.event.event.repositories.AttendRepository;
import com.facens.pooii.event.event.repositories.EventRepository;
import com.facens.pooii.event.event.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AttendService attendService;

    @Autowired
    AttendRepository attendRepository;

    public List<Ticket> getTicketByEvent(Long idEvent) {
        Event event = eventService.getEventById(idEvent);
        return event.getTickets();
    }

    public Ticket insertTicket(Long idEvent, TicketInsertDTO dto) {
        ticketValidation(dto);
        Event event = eventService.getEventById(idEvent);
        Attend attend = attendService.getAttendById(dto.getAttendId());
        Ticket ticket = new Ticket(dto);

        ticket.setDate(Instant.now());
        ticket.setAttend(attend);
        ticket.setEvent(event);
        if (ticket.getType() == Type.PAYED) {
            ticket.getAttend().setBalance(dueTicket(ticket));
            ticket.getEvent().ticketPayedDecrement();
        } else {
            ticket.getEvent().ticketFreeDecrement();
        }
        ticket = ticketRepository.save(ticket);
        return ticket;
    }

    public double dueTicket(Ticket ticket) {
        double balance = 0;
        try {
            balance = ticket.getAttend().getBalance() - ticket.getPrice();
            if (balance >= 0) {
                return balance;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance unavailable", e);
        }
    }

    public void ticketValidation(TicketInsertDTO dto) {
        String log = "";
        try {
            if (dto.getType() == Type.FREE) {
                dto.setPrice(0.0);
            } else {
                if (dto.getPrice() <= 0) {
                    log = "Exception found: Price must be greater than $0";
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception found: " + log, e);
        }
    }

}
