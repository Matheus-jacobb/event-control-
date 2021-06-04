package com.facens.pooii.event.event.DTO;

import com.facens.pooii.event.event.entities.Type;

public class TicketInsertDTO {

    private Double price;
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
