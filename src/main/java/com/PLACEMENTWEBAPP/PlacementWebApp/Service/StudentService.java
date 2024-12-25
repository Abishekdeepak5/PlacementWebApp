package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.DriveRegistration;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.DriveStudentRegistrationRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.Driverepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;

    @Autowired
    private Driverepository driverepository;

    @Autowired
    private DriveStudentRegistrationRepository driveStudentRegistrationRepository;

    public  Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow();
    }

    public DriveRegistration registerForDrive(Long studentId,Long driveId) throws Exception {
        System.out.println(studentId);
      Student student1=getStudentById(studentId);
//      Student student1=student.get();

      Optional<Drive>drive=driverepository.findById(driveId);
      Drive drive1=drive.get();


      if(student1!=null && drive1!=null && drive1.getRegistrationClosingDate().after(new Date())
){
          DriveRegistration driveRegistration=new DriveRegistration();
          driveRegistration.setDrive(drive1);
          driveRegistration.setStudent(student1);
          driveRegistration.setStatus("registered");
          student1.getRegisteredDriveList().add(drive1);
          drive1.getRegisteredStudents().add(student1);
          studentRepository.save(student1);
          driverepository.save(drive1);
          return driveStudentRegistrationRepository.save(driveRegistration);
      }
      else{
          throw new Exception("student or drive is null or drive registration date is closed");
      }
    }
}
