package com.facens.pooii.event.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.EventInsertDTO;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getAllEvents(PageRequest pageRequest, String name, String place) {
        return eventRepository.find(pageRequest, name, place);
    }
    
    public Event getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        return event;
    }

    public Event insertEvent(EventInsertDTO dto) {
        Event event = new Event(dto);
        event = eventRepository.save(event);
        return event;
    }
    
}
