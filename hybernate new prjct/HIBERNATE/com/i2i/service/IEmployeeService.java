package com.i2i.service;

import com.i2i.model.Employee;
import com.i2i.model.Trainee;
import com.i2i.model.Trainer;
import com.i2i.dao.IEmployeeDao;
import com.i2i.dao.impl.EmployeeDaoImpl;
import java.util.List;
import com.i2i.dto.EmployeeDto;

public interface IEmployeeService<T> {

    public void addEmployee(T emp);
  
    public List<T> getAllEmployees();

    public T  getEmployeeById(String employeeId);   

    public void removeEmployeeById(String employeeId);
    
    public void  updateEmployeeById(String employeeId, long mobileNumber);

    public void associateEmployee(String traineeId, String trainerId);

    public List<EmployeeDto> getAssociates(String employeeId);

}