package com.i2i.dao;

import com.i2i.model.Trainer;
import com.i2i.model.Trainee;
import com.i2i.model.Employee;
import com.i2i.dao.IEmployeeDao;
import java.util.List;
import java.util.LinkedList;

public interface IEmployeeDao<T extends Employee> {

    public  void insertEmployee(T emp);

    public List<T>  retriveAllEmployees();

    public T retriveEmployeeById(String employeeId);

    public void deleteEmployeeById(String employeeId);

    public void updateEmployeeById(String employeeId, long mobileNumber);

    public void associateEmployee(String TraineeId, String TrainerId);
  
    public List<Employee> retriveAssociates(String employeeId);
}
 
