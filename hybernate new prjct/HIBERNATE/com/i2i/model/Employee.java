package com.i2i.model;

import java.time.LocalDate;
import java.time.Period;

public class Employee {

    private String employeeName;
    private String employeeId;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoin;
    private long mobileNumber;
        
    public String getEmployeeName() {
        return employeeName;
    }
   
    public String getEmployeeId() {
        return employeeId;
    }

    public long getEmployeeMobileNumber() {
        return mobileNumber;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
   
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return employeeDateOfJoin;
    }

    public void setEmployeeDateOfJoin(LocalDate employeeDateOfJoin) {
        this.employeeDateOfJoin = employeeDateOfJoin;
    }


    public void setEmployeeMobileNumber(long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public int getAgeFromDateOfBirth() {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(employeeDateOfBirth, currentDate).getYears());
    }

    public int getExperienceFromDateOfJoin() {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(employeeDateOfJoin, currentDate).getYears());
    }


    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
       
        stringBuilder.append("Name :").append(this.getEmployeeName()).append("\nID :")
                     .append(this.getEmployeeId()).append("\nMobile No :")
                     .append(this.getEmployeeMobileNumber()).append("\nAge :")
                     .append(this.getAgeFromDateOfBirth()).append("\nExperience :")
                     .append(this.getExperienceFromDateOfJoin());
        
        return stringBuilder.toString(); 
    }  
}
    
        
        