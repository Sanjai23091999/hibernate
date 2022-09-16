package com.i2i.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.CascadeType;

@Entity
public class TraineeInfo extends Employee{

    @ManyToMany(cascade = CascadeType.ALL,mappedBy="trainees") 
    private List<TrainerInfo> trainers;
        

    
    public List<TrainerInfo> getTrainers(){
	return trainers;
    }
                                        
    public void setTrainers(List<TrainerInfo> trainers) {
	this.trainers = trainers;
    }

}
       




















