package com.i2i.model;

import java.util.List;

public class Trainer extends Employee {
    
    private List<Trainee> trainees;
    
    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }
    
    public String toString() {
        return super.toString();
    } 
} 
    

