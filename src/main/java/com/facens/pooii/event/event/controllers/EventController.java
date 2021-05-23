package com.facens.pooii.event.event.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import com.facens.pooii.event.event.DTO.EventInsertDTO;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Ticket;
import com.facens.pooii.event.event.services.EventService;
import com.facens.pooii.event.event.services.PlaceService;
import com.facens.pooii.event.event.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<Page<Event>> getAllEvents(

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "name", defaultValue = "") String name,
            // @RequestParam(value = "place", defaultValue = "") String place,
            @RequestParam(value = "startDate", defaultValue = "0000-01-01") String startDate,
            @RequestParam(value = "description", defaultValue = "") String description

    )

    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<Event> events = eventService.getAllEvents(pageRequest, name, startDate, description);
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok().body(event);
    }

    @PostMapping("/{idAdmin}")
    public ResponseEntity<Event> insertEvent(@PathVariable Long idAdmin, @RequestBody EventInsertDTO insertDTO) {
        Event event = eventService.insertEvent(idAdmin, insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventInsertDTO updatDTO) {
        Event event = eventService.updateEvent(id, updatDTO);
        return ResponseEntity.ok().body(event);
    }

    // AF

    @PostMapping("/{idEvent}/places/{idPlace}")
    public ResponseEntity<Event> insertEventPlace(@RequestBody EventInsertDTO dto, @PathVariable Long idEvent,
            @PathVariable Long idPlace) {
        Event event = eventService.getEventById(idEvent);
        // event.setPlaces(placeService.getPlaceById(idPlace));
        return ResponseEntity.ok().body(event);
    }

    // @GetMapping("/{id}/tickets")
    // public ResponseEntity<List<Ticket>> getAllTickets(@PathVariable Long id) {
    //     List<Ticket> tickets = ticketService.getTicketByEvent(id);
    //     return ResponseEntity.ok().body(tickets);
    // }

}
