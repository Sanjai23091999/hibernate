  package com.i2i.dao.impl;
import org.hibernate.*;  
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.i2i.model.Trainer;
import com.i2i.model.Trainee;
import com.i2i.model.Employee;
import com.i2i.dao.IEmployeeDao;
import com.i2i.connection.CreateConnection;
import com.i2i.entity.TraineeInfo;
import com.i2i.entity.TrainerInfo;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import org.hibernate.query.Query;
import javax.persistence.TypedQuery;  


public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> {

    private T employee;

    public EmployeeDaoImpl(T employee) {
        this.employee = employee;
    }

    @Override        
    public  void insertEmployee(T employee) {
       
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(TrainerInfo.class);
            configuration.addAnnotatedClass(TraineeInfo.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        if (employee instanceof Trainee) {
            Session session  = sessionFactory.openSession();
    	    Transaction transaction =session.beginTransaction();

            TraineeInfo traineeInfo = new TraineeInfo();
            
            traineeInfo.setTraineeId(employee.getEmployeeId());
            traineeInfo.setTraineeName(employee.getEmployeeName());
            traineeInfo.setTraineeDateOfBirth(employee.getEmployeeDateOfBirth());
            traineeInfo.setTraineeDateOfJoin(employee.getEmployeeDateOfJoin());
            traineeInfo.setTraineeMobileNumber(employee.getEmployeeMobileNumber());
           
            session.persist(traineeInfo);
       
            transaction.commit();
            session.close();          
            
        } else {
            Session session  = sessionFactory.openSession();
    	    Transaction transaction =session.beginTransaction();

            TrainerInfo trainerInfo = new TrainerInfo();
            
            trainerInfo.setTrainerId(employee.getEmployeeId());
            trainerInfo.setTrainerName(employee.getEmployeeName());
            trainerInfo.setTrainerDateOfBirth(employee.getEmployeeDateOfBirth());
            trainerInfo.setTrainerDateOfJoin(employee.getEmployeeDateOfJoin());
            trainerInfo.setTrainerMobileNumber(employee.getEmployeeMobileNumber());
            System.out.println("adding trainer");
            session.persist(trainerInfo);
       
            transaction.commit();
            session.close();          
       }
    }

    @Override
    public List<T> retriveAllEmployees() {

         Configuration configuration = new Configuration();
         configuration.configure("hibernate.cfg.xml");
         configuration.addAnnotatedClass(TrainerInfo.class);
         configuration.addAnnotatedClass(TraineeInfo.class);

         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
         SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         if (employee instanceof Trainer) {
   
            Session session  = sessionFactory.openSession();
    	    Transaction transaction =session.beginTransaction();
            List<Trainer> trainers = new ArrayList<>();

            TypedQuery query = session.createQuery("from TrainerInfo trainerInfo");  
            List<TrainerInfo> trainersInfo = query.getResultList();    
        
            for (TrainerInfo trainerInfo : trainersInfo) {
                 Trainer trainer = new Trainer();
                 trainer.setEmployeeId(trainerInfo.getTrainerId());
                 trainer.setEmployeeName(trainerInfo.getTrainerName());
                 trainer.setEmployeeDateOfBirth(trainerInfo.getTrainerDateOfBirth());
                 trainer.setEmployeeDateOfJoin(trainerInfo.getTrainerDateOfJoin());
                 trainer.setEmployeeMobileNumber(trainerInfo.getTrainerMobileNumber());
                 System.out.println("displaying trainer");
                 trainers.add(trainer);
                                                           
            }
            transaction.commit();
            session.close();          
            return (List<T>)trainers;
            

        } else {
            Session session  = sessionFactory.openSession();
    	    Transaction transaction =session.beginTransaction();
            List<Trainee> trainees = new ArrayList<>();

            TypedQuery query = session.createQuery("from TraineeInfo traineeInfo");  
            List<TraineeInfo> traineesInfo = query.getResultList();    
        
            for (TraineeInfo traineeInfo : traineesInfo) {
                 Trainee trainee = new Trainee();
                 trainee.setEmployeeId(traineeInfo.getTraineeId());
                 trainee.setEmployeeName(traineeInfo.getTraineeName());
                 trainee.setEmployeeDateOfBirth(traineeInfo.getTraineeDateOfBirth());
                 trainee.setEmployeeDateOfJoin(traineeInfo.getTraineeDateOfJoin());
                 trainee.setEmployeeMobileNumber(traineeInfo.getTraineeMobileNumber());
                 trainees.add(trainee);
                
           }  
            transaction.commit();
            session.close();                                               
            return (List<T>)trainees;
        }
    }

    @Override
    public  T retriveEmployeeById(String employeeId) {
        
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(TrainerInfo.class);
            configuration.addAnnotatedClass(TraineeInfo.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         
            if (employee instanceof Trainer) {
                Session session  = sessionFactory.openSession();
    	        Transaction transaction =session.beginTransaction();
                TypedQuery query = session.createQuery("from TrainerInfo trainerInfo");  
                List<TrainerInfo> trainersInfo = query.getResultList(); 
            
                Trainer selectedTrainer = null;
                for (TrainerInfo trainerInfo : trainersInfo) {
                    if (trainerInfo.getTrainerId().equals(employeeId)) {
                        Trainer trainer = new Trainer();
                        trainer.setEmployeeId(trainerInfo.getTrainerId());
                        trainer.setEmployeeName(trainerInfo.getTrainerName());
                        trainer.setEmployeeDateOfBirth(trainerInfo.getTrainerDateOfBirth());
                        trainer.setEmployeeDateOfJoin(trainerInfo.getTrainerDateOfJoin());
                        trainer.setEmployeeMobileNumber(trainerInfo.getTrainerMobileNumber());          
                        selectedTrainer = trainer;
                     }
                }            
                return (T)selectedTrainer;
            } else {         
                 Session session  = sessionFactory.openSession();
    	         Transaction transaction =session.beginTransaction();
                 TypedQuery query = session.createQuery("from TraineeInfo traineeInfo");  
                 List<TraineeInfo> traineesInfo = query.getResultList(); 
            
                Trainee selectedTrainee = null;
                for (TraineeInfo traineeInfo : traineesInfo) {
                    if (traineeInfo.getTraineeId().equals(employeeId)) {
                        Trainee trainee = new Trainee();
                        trainee.setEmployeeId(traineeInfo.getTraineeId());

                        trainee.setEmployeeName(traineeInfo.getTraineeName());
                        trainee.setEmployeeDateOfBirth(traineeInfo.getTraineeDateOfBirth());
                        trainee.setEmployeeDateOfJoin(traineeInfo.getTraineeDateOfJoin());
                        trainee.setEmployeeMobileNumber(traineeInfo.getTraineeMobileNumber());
                        selectedTrainee = trainee;
                    } 
                }           
             return (T)selectedTrainee;
            }
    }

    @Override
    public void deleteEmployeeById(String employeeId) {
        if (employee instanceof Trainer) {
            try ( Connection connection = CreateConnection.getInstance()) {  
                String trainerDeleteQuery = "delete from Trainers where Trainer_Id =?";
                PreparedStatement preparedStatement = connection.prepareStatement(trainerDeleteQuery);
                preparedStatement.setString(1, employeeId);
                preparedStatement.executeUpdate();
             }  catch (Exception e) {
                e.printStackTrace();
             }
         } else {
              try ( Connection connection = CreateConnection.getInstance()) {  
                  String traineeDeleteQuery = "delete from Trainees where Trainee_Id =?";
                  PreparedStatement preparedStatement = connection.prepareStatement(traineeDeleteQuery);
                  preparedStatement.setString(1, employeeId);
                  preparedStatement.executeUpdate();
              }  catch (Exception e) {
                  e.printStackTrace();
              }
        }
    } 

    @Override
    public void updateEmployeeById(String employeeId, long mobileNumber) {
        if (employee instanceof Trainee) {
            try ( Connection connection = CreateConnection.getInstance()) {  
                String traineeUpdateQuery = "Update Trainees Set Trainee_mobile_Number = ? where  Trainee_Id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(traineeUpdateQuery);
                preparedStatement.setLong(1, mobileNumber);
                preparedStatement.setString(2, employeeId);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }             
         } else {
            try ( Connection connection = CreateConnection.getInstance()) {  
                String trainerUpdateQuery = "Update Trainers Set Trainer_mobile_Number = ? where  Trainer_Id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(trainerUpdateQuery);
                preparedStatement.setLong(1, mobileNumber);
                preparedStatement.setString(2, employeeId);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }             
        }
    } 

   public void associateEmployee(String TraineeId, String TrainerId) {

       if (employee instanceof Trainee) {
           try ( Connection connection = CreateConnection.getInstance()) {  
               String traineeInsertQuery = "INSERT INTO Link_Employee(Trainee_Id, Trainer_Id) values ( ?, ?)";
               PreparedStatement preparedStatement = connection.prepareStatement(traineeInsertQuery);
               preparedStatement.setString(1, TraineeId);
               preparedStatement.setString(2, TrainerId); 
               preparedStatement.executeUpdate();
            } catch (Exception e) {
               e.printStackTrace();
            } 
        } else {
            try ( Connection connection = CreateConnection.getInstance()) {  
                String trainerInsertQuery = "INSERT INTO Link_Employee(Trainee_Id, Trainer_Id) values ( ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(trainerInsertQuery);
                preparedStatement.setString(1, TraineeId);
                preparedStatement.setString(2, TrainerId); 
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } 
         }
    }

    public List<Employee> retriveAssociates(String employeeId) {

        if (employee instanceof Trainee) {
            String traineeDisplayQuery = " select * from Link_employee inner join Trainers on Trainers.Trainer_Id = Link_employee.Trainer_Id inner join Trainees on Trainees.Trainee_Id = Link_employee.Trainee_Id where Trainers.Trainer_Id = ?";
         
            List<T> employees = new ArrayList<>();
            ResultSet resultSet = null;
            try (Connection connection = CreateConnection.getInstance(); 
                 PreparedStatement prepareStatement = connection.prepareStatement(traineeDisplayQuery);) {                
                     prepareStatement.setString(1, employeeId);
                     resultSet = prepareStatement.executeQuery();
                     while(resultSet.next()) { 
		         Trainee trainee = new Trainee();
                         Trainer trainer = new Trainer();

                         trainee.setEmployeeId(resultSet.getString("Trainees.Trainee_Id"));
                         trainee.setEmployeeName(resultSet.getString("Trainees.Trainee_Name"));
                         trainee.setEmployeeMobileNumber(resultSet.getLong("Trainees.Trainee_mobile_Number"));
                         trainee.setEmployeeDateOfBirth(resultSet.getDate("Trainees.Date_of_Birth").toLocalDate());
                         trainee.setEmployeeDateOfJoin(resultSet.getDate("Trainees.Date_of_Join").toLocalDate());
                         employees.add((T)trainee);

                         trainer.setEmployeeId(resultSet.getString("Trainers.Trainer_Id"));
                         trainer.setEmployeeName(resultSet.getString("Trainers.Trainer_Name"));
                         trainer.setEmployeeMobileNumber(resultSet.getLong("Trainers.Trainer_mobile_Number"));
                         trainer.setEmployeeDateOfBirth(resultSet.getDate("Trainers.Date_of_Birth").toLocalDate());
                         trainer.setEmployeeDateOfJoin(resultSet.getDate("Trainers.Date_of_Join").toLocalDate());
                         employees.add((T)trainer);
                     
                }            
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (List<Employee>)employees;
        } else {
            String trainerDisplayQuery = " select * from Link_Employee inner join Trainers on Trainers.Trainer_Id = Link_employee.Trainer_Id  inner join Trainees on Trainees.Trainee_Id = Link_employee.Trainee_Id where Trainees.Trainee_Id = ?";
         
            List<Employee> employees = new ArrayList<>();
            ResultSet resultSet = null;
            try (Connection connection = CreateConnection.getInstance(); 
                 PreparedStatement prepareStatement = connection.prepareStatement(trainerDisplayQuery);) {       
                     prepareStatement.setString(1, employeeId);
                     resultSet = prepareStatement.executeQuery();
                     while(resultSet.next()) { 
		         Trainee trainee = new Trainee();
                         Trainer trainer = new Trainer();

                         trainee.setEmployeeId(resultSet.getString("Trainees.Trainee_Id"));
                         trainee.setEmployeeName(resultSet.getString("Trainees.Trainee_Name"));
                         trainee.setEmployeeMobileNumber(resultSet.getLong("Trainees.Trainee_mobile_Number"));
                         trainee.setEmployeeDateOfBirth(resultSet.getDate("Trainees.Date_of_Birth").toLocalDate());
                         trainee.setEmployeeDateOfJoin(resultSet.getDate("Trainees.Date_of_Join").toLocalDate());
                         employees.add((T)trainee);

                         trainer.setEmployeeId(resultSet.getString("Trainers.Trainer_Id"));
                         trainer.setEmployeeName(resultSet.getString("Trainers.Trainer_Name"));
                         trainer.setEmployeeMobileNumber(resultSet.getLong("Trainers.Trainer_mobile_Number"));
                         trainer.setEmployeeDateOfBirth(resultSet.getDate("Trainers.Date_of_Birth").toLocalDate());
                         trainer.setEmployeeDateOfJoin(resultSet.getDate("Trainers.Date_of_Join").toLocalDate());
                         employees.add((T)trainer);
                }            
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (List<Employee>)employees;
        }
    }
}