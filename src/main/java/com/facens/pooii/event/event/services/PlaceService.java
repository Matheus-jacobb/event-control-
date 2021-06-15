package com.facens.pooii.event.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.PlaceInsertDTO;
import com.facens.pooii.event.event.entities.Event;
import com.facens.pooii.event.event.entities.Place;
import com.facens.pooii.event.event.repositories.EventRepository;
import com.facens.pooii.event.event.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    EventRepository eventRepository;

    public Page<Place> getAllPlace(PageRequest pageRequest) {
        return placeRepository.findAll(pageRequest);
    }

    public Place getPlaceById(Long id) {
        Optional<Place> op = placeRepository.findById(id);
        Place place = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found."));
        return place;
    }

    public Place insertPlace(PlaceInsertDTO dto) {
        Place place = new Place(dto);
        place = placeRepository.save(place);
        return place;
    }

    public boolean deletePlace(Long id) {
        try {
            Place place = placeRepository.getOne(id);
            List<Event> events = eventRepository.findAll();
            boolean ans = deletePlaceValidation(place, events);
            if (ans) {
                placeRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found", e);
        }
    }

    public boolean deletePlaceValidation(Place place, List<Event> events) {
        for (Event aux : events) {
            //LOGICA INCORRETA
            if (aux.getPlaces().contains(place))
                return false;
        }
        return true;
    }

    public Place updatePlace(Long id, PlaceInsertDTO dto) {
        try {
            Place place = placeRepository.getOne(id);
            place.setName(dto.getName());
            place.setAddress(dto.getAddress());
            place = placeRepository.save(place);
            return place;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found.");
        }
    }

}
