package com.facens.pooii.event.event.entities;

import java.time.Instant;


public class Ticket{

    private Long id;
    private enum type{

    };
    private Instant date;
    private Double price;
  
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

    public Ticket(){

    }
    //bugFixing
}
