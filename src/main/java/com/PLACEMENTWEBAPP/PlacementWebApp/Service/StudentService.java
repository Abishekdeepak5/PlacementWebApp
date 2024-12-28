package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.MarkDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.DriveRegistration;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Marks;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.DriveStudentRegistrationRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.Driverepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.MarkRepository;
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
   private MarkRepository markRepository;

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
          if(student1.getMarks().getCgpa()<drive1.getEligibleCgpa()){
              throw new Exception("not eligible because cgpa is not enough");
          }
          if(student1.isPlaced()){
              throw new Exception("not eligibile because you are plaeced");
          }
          if(!drive1.isHistoryOfAllowed()){
              if(student1.getMarks().isHistoryOfArrear()){
                  throw  new Exception("not eligible beacuse you had a history of arrear");
              }
          }
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
    public Student uploadMarks(MarkDto markDto) throws Exception {
        try{Student student=getStudentById(markDto.getStudentId());
            if(student.getMarks()==null){
        Marks marks=new Marks();
        marks.setCgpa(markDto.getCgpa());
        marks.setCurrentBacklogs(markDto.getCurrentBacklogs());
        marks.setStudent(getStudentById(markDto.getStudentId()));
        marks.setDiploma(markDto.getDiploma());
        marks.setHSC1(markDto.getHSC1());
        marks.setHSC2(markDto.getHSC2());
        marks.setHistoryOfArrear(markDto.isHistoryOfArrear());
        int count=0;
        float sum=0;
        if(markDto.getSem1()!=0){
        marks.setSem1(markDto.getSem1());
        sum+= markDto.getSem1();
        count++;
        }
        if(markDto.getSem2()!=0){
        marks.setSem2(markDto.getSem2());
            sum+= markDto.getSem2();

            count++;}
        if(markDto.getSem3()!=0){
        marks.setSem3(markDto.getSem3());
            sum+= markDto.getSem3();

            count++;}
        if(markDto.getSem4()!=0){
        marks.setSem4(markDto.getSem4());
            sum+= markDto.getSem4();

            count++;}
        if(markDto.getSem5()!=0){
        marks.setSem5(markDto.getSem5());
            sum+= markDto.getSem5();

            count++;}
        if(markDto.getSem6()!=0){
        marks.setSem6(markDto.getSem6());
            sum+= markDto.getSem6();

            count++;}
        if(markDto.getSem7()!=0){
        marks.setSem7(markDto.getSem7());
            sum+= markDto.getSem7();
            count++;}

       float cgpa=sum/count;
marks.setCgpa(cgpa);
            System.out.println("marks are perfectly setted");
            System.out.println("student is founded");
student.setMarks(marks);
markRepository.save(marks);
            System.out.println("markRepository is correct");

return studentRepository.save(student);}
        else{
        throw new Exception("student marks is already uploaded");}
        }

        catch (Exception e){
            throw new Exception(e.getMessage()+"not found");
        }


    }
}
