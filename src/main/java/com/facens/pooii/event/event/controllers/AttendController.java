package com.facens.pooii.event.event.controllers;

import java.net.URI;

import com.facens.pooii.event.event.DTO.AttendInsertDTO;
import com.facens.pooii.event.event.entities.Attend;
import com.facens.pooii.event.event.services.AttendService;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
public class AttendController {

    @Autowired
    AttendService attendService;

    @GetMapping
    public ResponseEntity<Page<Attend>> getAllEvents(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage, 
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    )

    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<Attend> attends = attendService.getAllAttend(pageRequest);
        return ResponseEntity.ok().body(attends);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Attend> getAttendById(@PathVariable Long id) {
        Attend attend = attendService.getAttendById(id);
        return ResponseEntity.ok().body(attend);
    }

    @PostMapping
    public ResponseEntity<Attend> insertAttend(@RequestBody AttendInsertDTO dto) {
        Attend attend = attendService.insertAttend(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attend.getId()).toUri();
        return ResponseEntity.created(uri).body(attend);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttend(@PathVariable Long id) {
        attendService.deleteAttend(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attend> updateAttend(@PathVariable Long id, @RequestBody AttendInsertDTO dto) {
        Attend attend = attendService.updateAttend(id, dto);
        return ResponseEntity.ok().body(attend);
    }

}
