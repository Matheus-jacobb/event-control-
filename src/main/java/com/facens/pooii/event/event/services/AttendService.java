package com.facens.pooii.event.event.services;

import java.util.Optional;

import com.facens.pooii.event.event.DTO.AttendInsertDTO;
import com.facens.pooii.event.event.entities.Attend;
import com.facens.pooii.event.event.repositories.AttendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {

    @Autowired
    AttendRepository attendRepository;

    public Page<Attend> getAllAttend(PageRequest pageRequest) {
        return attendRepository.findAll(pageRequest);
    }

    public Attend getAttendById(Long id) {
        Optional<Attend> op = attendRepository.findById(id);
        Attend attend = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found"));
        return attend;
    }

    public Attend insertAttend(AttendInsertDTO dto) {
        Attend attend = new Attend(dto);
        attend.setBalance(0.0);
        attend = attendRepository.save(attend);
        return attend;
    }

    public void deleteAttend(Long id) {
        try {
            attendRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
        }
    }

    public Attend updateAttend(Long id, AttendInsertDTO dto) {
        try {
            Attend attend = attendRepository.getOne(id);
            attend.setName(dto.getName());
            attend.setEmail(dto.getEmail());
            attend.setBalance(dto.getBalance());
            attend = attendRepository.save(attend);
            return attend;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
        }
    }

}
