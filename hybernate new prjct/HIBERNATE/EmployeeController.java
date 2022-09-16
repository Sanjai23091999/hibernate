

import com.i2i.util.EmployeeUtil;
import com.i2i.model.Employee;
import com.i2i.model.Trainee;
import com.i2i.model.Trainer;
import com.i2i.service.IEmployeeService;
import com.i2i.service.impl.EmployeeServiceImpl;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.i2i.dto.TrainerDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.EmployeeDto;
import com.i2i.exception.IdDoesNotExistException;

public class EmployeeController {

    private  Scanner scanner = new Scanner(System.in);
    
    private IEmployeeService<TraineeDto> traineeService = new EmployeeServiceImpl(new TraineeDto());
    private IEmployeeService<TrainerDto> trainerService = new EmployeeServiceImpl(new TrainerDto());

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public static void main(String[] args) {

        EmployeeController employeeController = new EmployeeController();
        
        System.out.println("Welcome to Ideas2IT Employee management portal!!");    
        employeeController.init();     
    }

    public void init() {
        boolean isContinue = true;
        while(isContinue) {
            logger.info("Please enter one of the below options to proceed further");
            logger.info(" Enter 1 for add details \n Enter 2 for display Details \n Enter 3 for Delete details"
                               +" \n Enter 4 for update \n Enter 5 for Associate Trainer or Trainee \n Enter Any other for Exit");                               
            int userDecision = scanner.nextInt();
            switch (userDecision) {
            case 1:
                logger.info(" Enter 1 for add Trainer \n Enter 2 add Trainee");
                int userChoice = scanner.nextInt();

                if (userChoice == 1) {
                    createEmployee(userChoice);
                } else if (userChoice == 2) {
                    createEmployee(userChoice);
                }
                break;

            case 2: 
                logger.info(" Enter 1 for Display All Employee \n Enter 2 for Display specific Employee" );
                int userInput = scanner.nextInt();
                switch (userInput) {
                case 1:
                    scanner.nextLine();
                    logger.info("Enter 1 for Display  All Trainers \n Enter 2 for Display All Trainees \n Enter 3 for Display Associates");
                    int userSelect = scanner.nextInt();
                    switch (userSelect) {
                    case 1:
                        displayAllTrainers();
                        break;

                    case 2: 
                        displayAllTrainees();
                        break;

                    case 3: 
                        displayAssociatedTrainers();
                        displayAssociatedTrainees();
                        break;
                    }
                    break;
                 case 2:
                     logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                     int userChoiceForDisplay = scanner.nextInt();
                     scanner.nextLine();
                     if (userChoiceForDisplay == 1) {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTrainerById(id);
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     } else {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTraineeById(id); 
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     }                      
                    break;
                 }
                 break;

            case 3:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userChoiceForRemove = scanner.nextInt();

                     if (userChoiceForRemove == 1) {
                         scanner.nextLine();
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         trainerService.removeEmployeeById(id);
                     } else {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         traineeService.removeEmployeeById(id); 
                     }
                 break;
            
            case 4:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userChoiceForUpdate = scanner.nextInt();

                     if (userChoiceForUpdate == 1) {
                         updateEmployee(userChoiceForUpdate);
                     } else if (userChoiceForUpdate == 2){
                         updateEmployee(userChoiceForUpdate);
                     }
                 break;

             case 5:
                 logger.info(" Enter 1 for Associate  Trainee for Trainer \nEnter 2 for Associate Trainer for Trainee");                  
                 int userChoiceForAssociation = scanner.nextInt();

                 if (userChoiceForAssociation == 1) {
                     associatedEmployee(userChoiceForAssociation);
                 } else if (userChoiceForAssociation == 2) {
                     associatedEmployee(userChoiceForAssociation);
                 }
                 break;

            default:
                isContinue = false;
            }
        }
    }
             
    public void createEmployee(int userInput) {
          
        scanner.nextLine();
        logger.info("Enter Employee Name");
        String employeeName = scanner.nextLine();      
        logger.info("Enter date of birth in YYYY-MM-DD format: ");
        String dateOfBirth = scanner.nextLine();
        LocalDate employeeDateOfBirth = LocalDate.parse(dateOfBirth);
        logger.info("Enter date of Join in YYYY-MM-DD format: ");
        String dateOfJoin = scanner.nextLine();
        LocalDate employeeDateOfJoin = LocalDate.parse(dateOfJoin);
        logger.info("Enter your mobile Number");
        long mobileNumber = scanner.nextLong();
        String employeeId = EmployeeUtil.getId();

        if (userInput == 2) {

            TraineeDto traineeDto = new TraineeDto();

            traineeDto.setEmployeeName(employeeName);
            traineeDto.setEmployeeDateOfBirth(employeeDateOfBirth);
            traineeDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            traineeDto.setEmployeeId(employeeId); 
            traineeDto.setEmployeeMobileNumber(mobileNumber);
            traineeService.addEmployee(traineeDto);

        } else if (userInput == 1) {


            TrainerDto trainerDto = new TrainerDto();

            trainerDto.setEmployeeName(employeeName);
            trainerDto.setEmployeeDateOfBirth(employeeDateOfBirth);
            trainerDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            trainerDto.setEmployeeId(employeeId); 
            trainerDto.setEmployeeMobileNumber(mobileNumber);
            trainerService.addEmployee(trainerDto);
        }           
    }

    public void displayAllTrainers() {
        for (TrainerDto trainerDto : (List<TrainerDto>)trainerService.getAllEmployees()) {
            logger.info(trainerDto.toString());     
        }
    }

    public void displayAllTrainees() {
        for (TraineeDto traineeDto : (List<TraineeDto>)traineeService.getAllEmployees()) {
            logger.info(traineeDto.toString());
        } 
    } 
  
    public void displayTrainerById(String employeeId) throws IdDoesNotExistException {
        TrainerDto trainerDto = trainerService.getEmployeeById(employeeId);
        if (trainerService.getEmployeeById(employeeId) == null) {
            throw new IdDoesNotExistException("ID does not exists");
        } else { 
            logger.info(trainerDto.toString());
        }
    }

    public void displayTraineeById(String employeeId) throws IdDoesNotExistException {
        TraineeDto traineeDto = traineeService.getEmployeeById(employeeId);
        if (traineeService.getEmployeeById(employeeId) == null) {
            throw new IdDoesNotExistException("ID does not exists");
        } else {
            logger.info(traineeDto.toString());
        }
    }

    public void updateEmployee(int userChoiceForUpdate) {
        scanner.nextLine();
        logger.info("Please Enter your EmployeeId");
        String employeeId = scanner.nextLine();
        logger.info("Please Enter your Mobile Bumber");
        long mobileNumber = scanner.nextInt();

        if (userChoiceForUpdate == 1) {
           trainerService.updateEmployeeById(employeeId, mobileNumber);
        } else {
           traineeService.updateEmployeeById(employeeId, mobileNumber);
        }
    }

    public void associatedEmployee(int userChoice) {

        if (userChoice == 2) {
            scanner.nextLine();
            logger.info("Enter the Trainee employeeId ");
            String traineeId = scanner.nextLine();

            logger.info("Enter the Trainers Id you want to add");
            String[] trainerIds = scanner.nextLine().split(",");

            for (int i = 0; i<trainerIds.length; i++) {
                traineeService.associateEmployee(traineeId, trainerIds[i]);
            }
        } else if (userChoice == 1) {
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            String trainerId = scanner.nextLine();

            logger.info("Enter the Trainees Id you want to add");
            String[] traineeIds = scanner.nextLine().split(",");

            for (int i = 0; i<traineeIds.length; i++) {
                trainerService.associateEmployee(traineeIds[i], trainerId); 
            }
        } 
    } 

   public void displayAssociatedTrainers() {
        scanner.nextLine();
        logger.info("Enter the Trainee Id ");
        String employeeId = scanner.nextLine();
        boolean flag = true;
        for (EmployeeDto employeeDto : trainerService.getAssociates(employeeId)) {
            if (employeeDto instanceof TraineeDto && flag == true ) {
                logger.info(employeeDto.toString());
                logger.info("Trainers List");
                flag = false;
            }
        } 
        for (EmployeeDto employeeDto : trainerService.getAssociates(employeeId)) {
            if (employeeDto instanceof TrainerDto) {
                logger.info(employeeDto.toString()); 
            }
        }
    }

    public void displayAssociatedTrainees() {
        scanner.nextLine();
        logger.info("Enter the Trainer Id ");
        String employeeId = scanner.nextLine();
        boolean flag = true;
        for (EmployeeDto employeeDto : traineeService.getAssociates(employeeId)) {
            if (employeeDto instanceof TrainerDto && flag == true ) {
                logger.info(employeeDto.toString());
                logger.info("Trainees List");
                flag = false;
            }
        }
        for (EmployeeDto employeeDto : traineeService.getAssociates(employeeId)) {
            if (employeeDto instanceof TraineeDto) {
                logger.info(employeeDto.toString()); 
            }
        }
    }
     
        
}


        
    
