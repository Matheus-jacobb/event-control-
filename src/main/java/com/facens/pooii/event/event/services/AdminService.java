package com.facens.pooii.event.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.AdminInsertDTO;
import com.facens.pooii.event.event.entities.Admin;
import com.facens.pooii.event.event.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Page<Admin> getAllAdmin(PageRequest pageRequest) {
        return adminRepository.findAll(pageRequest);
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> op = adminRepository.findById(id);
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        return admin;
    }

    public Admin insertAdmin(AdminInsertDTO dto) {
        Admin admin = new Admin(dto);
        admin = adminRepository.save(admin);
        return admin;
    }

    public void deleteAdmin(Long id) {
        try {
            adminRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    }

    public Admin updateAdmin(Long id, AdminInsertDTO dto) {
        try {
            Admin admin = adminRepository.getOne(id);
            admin.setName(dto.getName());
            admin.setEmail(dto.getEmail());
            admin.setPhoneNumber(dto.getPhoneNumber());
            admin = adminRepository.save(admin);
            return admin;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    }

}
