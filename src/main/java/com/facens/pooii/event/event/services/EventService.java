package com.facens.pooii.event.event.services;

import java.time.LocalDate;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.EventInsertDTO;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getAllEvents(PageRequest pageRequest, String name, String place, String startDate, String description) {
        return eventRepository.find(pageRequest, name, place, LocalDate.parse(startDate), description);
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

        public Event updateEvent(Long id, EventInsertDTO dto) {
        try {
            Event event = eventRepository.getOne(id);
            event.setName(dto.getName());
            event.setDescription(dto.getDescription());
            event.setPlace(dto.getPlace());
            event.setStartDate(dto.getStartDate());
            event.setEndDate(dto.getEndDate());
            event.setStartTime(dto.getStartTime());
            event.setEndTime(dto.getEndTime());
            event.setEmail(dto.getEmail());
            event = eventRepository.save(event);
            return event;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    public void deleteEvent(Long id) {
        try{
            eventRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }
    
    
}
