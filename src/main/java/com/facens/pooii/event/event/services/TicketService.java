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
import org.springframework.dao.EmptyResultDataAccessException;
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
        
        ticket.setPrice(event.getPriceTicket());
        ticket.setDate(Instant.now());
        ticket.setAttend(attend);
        ticket.setEvent(event);
        

        Boolean isFull = limitValidation(event, ticket.getType());

        try{
            if (isFull)
                throw new Exception();
            
            isPayed(ticket);
            ticket = ticketRepository.save(ticket);
            return ticket;
        }
        catch (Exception e){
             if (ticket.getType() == Type.FREE)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Free tickets is over");

             else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payed tickets is over");
        }

    }

    public void deleteTicket(Long id) {

        try {
            Ticket ticket = ticketRepository.getOne(id);
            ticket.getAttend().setBalance(ticket.getAttend().getBalance() + ticket.getPrice());

            if (ticket.getType() == Type.PAYED) 
                ticket.getEvent().ticketPayedIncrement();
            else 
                ticket.getEvent().ticketFreeIncrement();

            ticket = ticketRepository.save(ticket);
            ticketRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
    }

    /**
     * Validators
     */

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
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception found: " + log, e);
        }
    }

    public void isPayed(Ticket ticket){
        if (ticket.getType() == Type.PAYED) {
            ticket.getAttend().setBalance(dueTicket(ticket));
            ticket.getEvent().ticketPayedDecrement();
        } else {
            ticket.getEvent().ticketFreeDecrement();
        }
    }

    public boolean limitValidation(Event event, Type type){
        if(event.getAmountFreeTickets() == 0 && type==Type.FREE){
            return true;
        }

        else if(event.getAmountPayedTickets() == 0 && type==Type.PAYED){
            return true;
        }

        else{
            return false;
        }
    }

}
