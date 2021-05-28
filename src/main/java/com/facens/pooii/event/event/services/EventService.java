package com.facens.pooii.event.event.services;

import java.time.LocalDate;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.EventInsertDTO;
import com.facens.pooii.event.event.entities.Admin;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Place;
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

    @Autowired
    private AdminService adminService;

    @Autowired
    private PlaceService placeService;

    public Page<Event> getAllEvents(PageRequest pageRequest, String name, String startDate, String description) {
        return eventRepository.find(pageRequest, name, LocalDate.parse(startDate), description);
    }

    public Event getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        return event;
    }

    public Event insertEvent(Long idAdmin, EventInsertDTO dto) {
        String log = "";
        Event event = new Event(dto);
        Admin admin = adminService.getAdminById(idAdmin);
        event.setAdmin(admin);
        admin.addEvents(event);
        try {
            if (event.getStartDate().isBefore(LocalDate.now())) {
                log = "Disagreement with date rule. Start date before today!";
                throw new Exception();
            } else if (event.getEndDate().isBefore(event.getStartDate())) {
                log = "Disagreement with date rule. End date before start date!";
                throw new Exception();
            } else {
                event = eventRepository.save(event);
                return event;
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception found. " + log, e);
        }
    }

    public void deleteEvent(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    public Event updateEvent(Long id, EventInsertDTO dto) {
        String log = "";
        try {
            if (dto.getStartDate().isBefore(LocalDate.now())) {
                log = "Disagreement with date rule. Start date before today!";
                throw new Exception();
            } else if (dto.getEndDate().isBefore(dto.getStartDate())) {
                log = "Disagreement with date rule. End date before start date!";
                throw new Exception();
            } else {
                try {
                    Event event = eventRepository.getOne(id);
                    if(event.getEndDate().isBefore(LocalDate.now())){
                        log = "Unable to update a closed event!";
                        throw new Exception();
                    }
                    event.setName(dto.getName());
                    event.setDescription(dto.getDescription());
                    event.setStartDate(dto.getStartDate());
                    event.setEndDate(dto.getEndDate());
                    event.setStartTime(dto.getStartTime());
                    event.setEndTime(dto.getEndTime());
                    event.setEmail(dto.getEmail());
                    event = eventRepository.save(event);
                    return event;
                } catch (Exception e) {
                    log = "Event not found.";
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception found. " + log, e);
        }
    }

    public Event insertEventPlace(Long idEvent, Long idPlace){
        Event event = eventRepository.getOne(idEvent);
        Place place = placeService.getPlaceById(idPlace);
        event.addPlaces(place);
        event = eventRepository.save(event);
        return event;
    }

    public void deleteEventPlace(Long idEvent, Long idPlace){
        Event event = eventRepository.getOne(idEvent);
        event.removePlaces(placeService.getPlaceById(idPlace));
        event = eventRepository.save(event);
    }

}
