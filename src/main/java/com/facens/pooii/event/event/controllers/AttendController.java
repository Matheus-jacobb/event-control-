package com.facens.pooii.event.event.controllers;

import java.net.URI;
import java.util.List;

import com.facens.pooii.event.event.DTO.AttendInsertDTO;
import com.facens.pooii.event.event.entities.Attend;
import com.facens.pooii.event.event.services.AttendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
public class AttendController {

    @Autowired
    AttendService attendService;

    @GetMapping
    public ResponseEntity<List<Attend>> getAllAttend() {
        List<Attend> attends = attendService.getAllAttend();
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
