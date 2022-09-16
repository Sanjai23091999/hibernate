package com.i2i.model;

import java.util.List;

public class Trainee extends Employee {

    private List<Trainer> trainers;
    
    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers= trainers;
    }
    
    public String toString() {
        return super.toString();
    }  
}

       




















