package com.facens.pooii.event.event.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.facens.pooii.event.event.DTO.TicketInsertDTO;

@Entity
@Table(name = "TB_TICKET")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant date;
    private Double price;
    private Type type;

    public Ticket() {

    }

    public Ticket(Long id, Instant date, Double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public Ticket(TicketInsertDTO dto) {
        this.price = dto.getPrice();
        this.type = dto.getType();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Ticket(Instant date, Double price) {
        this.date = date;
        this.price = price;
    }

    /**
     * Hash code and equals
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
