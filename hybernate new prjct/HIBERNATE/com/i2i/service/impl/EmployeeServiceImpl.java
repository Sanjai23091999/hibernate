package com.i2i.service.impl;

import com.i2i.service.IEmployeeService;
import com.i2i.model.Employee;
import com.i2i.model.Trainer;
import com.i2i.model.Trainee;
import com.i2i.dao.IEmployeeDao;
import com.i2i.dao.impl.EmployeeDaoImpl;
import java.util.List;
import com.i2i.dto.TrainerDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.EmployeeDto;
import com.i2i.convertor.Convertor;


public class EmployeeServiceImpl<T extends EmployeeDto> implements IEmployeeService<T> {

    private  static IEmployeeDao<Trainer> trainerDao = new EmployeeDaoImpl(new Trainer());
    private  static IEmployeeDao<Trainee> traineeDao = new EmployeeDaoImpl(new Trainee()); 

    private T employee;
    
    public EmployeeServiceImpl(T employee) {
        this.employee = employee;
    }

    @Override   
    public void addEmployee(T employeeDto) {
        if (employeeDto instanceof TrainerDto) {
            trainerDao.insertEmployee(Convertor.trainerDtoToTrainer((TrainerDto)employeeDto)); 
        } else {  
            traineeDao.insertEmployee(Convertor.traineeDtoToTrainee((TraineeDto)employeeDto));
        }
    }

    @Override
    public List<T> getAllEmployees() {
        if (employee instanceof TrainerDto) {
            return (List<T>)Convertor.trainerListTotrainerDtoList(trainerDao.retriveAllEmployees());            
        } else {
            return (List<T>)Convertor.traineeListTotraineeDtoList(traineeDao.retriveAllEmployees());
        }
    }

    @Override
    public T  getEmployeeById(String employeeId) {
        if (employee instanceof TrainerDto) {
            return (T)Convertor.trainerToTrainerDto(trainerDao.retriveEmployeeById(employeeId));
        } else {
            return (T)Convertor.traineeToTraineeDto(traineeDao.retriveEmployeeById(employeeId));
        }
    }


    @Override
    public void  removeEmployeeById(String employeeId) {
        if (employee instanceof TrainerDto) {
            trainerDao.deleteEmployeeById(employeeId);
        } else {
            traineeDao.deleteEmployeeById(employeeId);
        }
    }

    @Override
    public void  updateEmployeeById(String employeeId, long mobileNumber) {
        if (employee instanceof TrainerDto) {
            trainerDao.updateEmployeeById(employeeId, mobileNumber);
        } else {
            traineeDao.updateEmployeeById(employeeId, mobileNumber);       
        }  
    } 
    
    @Override
    public void associateEmployee(String traineeId, String trainerId) {
         if (employee instanceof TrainerDto) {
            trainerDao.associateEmployee(traineeId, trainerId);
        } else {
            traineeDao.associateEmployee(traineeId, trainerId);     
        } 
    } 

    public List<EmployeeDto> getAssociates(String employeeId) {
       if (employee instanceof TraineeDto) { 
           return Convertor.employeeListToEmployeeDtoList(traineeDao.retriveAssociates(employeeId));
       } else {
           return Convertor.employeeListToEmployeeDtoList(trainerDao.retriveAssociates(employeeId)); 
       }
    }

    
}


    