package com.facens.pooii.event.event.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.facens.pooii.event.event.DTO.AttendInsertDTO;

@Entity
@Table(name = "TB_ATTEND")
@PrimaryKeyJoinColumn(name = "BASE_USER_ID")
public class Attend extends BaseUser {
    private Double balance;

    @OneToMany
    @JoinColumn(name = "ATTEND_ID")
    private List<Ticket> tickets = new ArrayList<>();

    public Attend(Double balance) {
        this.balance = balance;
    }

    public Attend(Long id, String name, String email, Double balance) {
        super(id, name, email);
        this.balance = balance;
    }

    public Attend(AttendInsertDTO dto) {
        super(dto.getName(), dto.getEmail());
        this.balance = dto.getBalance();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // public List<Ticket> getTickets() {
    // return tickets;
    // }

    // public void addTicket(Ticket ticket) {
    // this.tickets.add(ticket);
    // }

}
