package com.facens.pooii.event.event.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventInsertDTO {
    
    private String name;
    private String description;
    private String place;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String email;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    


}
