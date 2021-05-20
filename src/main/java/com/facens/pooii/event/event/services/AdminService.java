package com.facens.pooii.event.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.pooii.event.event.DTO.AdminInsertDTO;
import com.facens.pooii.event.event.entities.Admin;
import com.facens.pooii.event.event.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id){
        Optional<Admin> op = adminRepository.findById(id);
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        return admin;
    }

    public Admin insertAdmin(AdminInsertDTO dto){
        Admin admin = new Admin(dto);
        admin = adminRepository.save(admin);
        return admin;
    }
    
}
