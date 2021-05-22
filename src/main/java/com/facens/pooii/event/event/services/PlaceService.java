package com.facens.pooii.event.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.PlaceInsertDTO;
import com.facens.pooii.event.event.entities.Place;
import com.facens.pooii.event.event.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    public List<Place> getAllPlace() {
        return placeRepository.findAll();
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

    public void deletePlace(Long id) {
        try {
            placeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }
    }

    public Place updatePlace(Long id, PlaceInsertDTO dto) {
        try{
            Place place = placeRepository.getOne(id);
            place.setName(dto.getName());
            place.setAddress(dto.getAddress());
            place = placeRepository.save(place);
            return place;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found.");
        }
    }

}
