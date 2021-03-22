package com.facens.pooii.event.event.controllers;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/events")

public class EventController {
    
    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = service.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = service.getEventById(id);
        return ResponseEntity.ok().body(event);
    }

}
