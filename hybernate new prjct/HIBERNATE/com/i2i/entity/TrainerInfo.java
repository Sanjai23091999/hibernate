package com.i2i.entity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class Trainer{

    @ManyToMany(cascade = CascadeType.ALL)  
    @JoinTable(name = "join_employee_table",   
    joinColumns = @JoinColumn(name = "Trainer_id"),   
    inverseJoinColumns = @JoinColumn(name = "Trainee_id")) 
    private List<TraineeInfo> trainees;

    public void setTrainee(List<TraineeInfo> trainees ) {
	this.trainees= trainees;
    }

    public List<TraineeInfo> getTrainee() {
	return trainees;
    }
        
    public String getTrainerName() {
        return TrainerName;
    }

}
       



    

