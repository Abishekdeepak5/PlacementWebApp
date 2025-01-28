package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.*;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;
   @Autowired
   private QueryRepo studentRepo;
    @Autowired
   private MarkRepository markRepository;

    @Autowired
    private Driverepository driverepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private DriveStudentRegistrationRepository driveStudentRegistrationRepository;

    public  Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow();
    }

    public DriveRegistration registerForDrive(Student student1,Long driveId) throws Exception {
      Optional<Drive>drive=driverepository.findById(driveId);
      Drive drive1=drive.get();
      System.out.print(student1+"\n"+drive1+"\n");
      if(student1!=null && drive1!=null && drive1.getRegistrationClosingDate().after(new Date())
){
          if(student1.getMarks()==null){
              throw new Exception("Please update mark");
          }
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
          drive1.setCount(drive1.getRegisteredStudents().size());
          studentRepository.save(student1);
          driverepository.save(drive1);
          return driveStudentRegistrationRepository.save(driveRegistration);
      }
      else{
          throw new Exception("student or drive is null or drive registration date is closed");
      }
    }
    public Student uploadMarks(Student student,Marks mark) throws Exception {
        try{
            if(student.getMarks()==null){
                mark.setStudent(student);
                markRepository.save(mark);
                student.setMarks(mark);
                return studentRepository.save(student);
            }else{
                Marks markInfo=student.getMarks();
                studentRepo.setMarksById(mark.getSSLC(),mark.getHSC1(),mark.getHSC2(),
                        mark.getDiploma(),mark.getSem1(),mark.getSem2(),mark.getSem3(),
                        mark.getSem4(),mark.getSem5(),mark.getSem6(),mark.getSem7(),
                        mark.getCgpa(),mark.getCurrentBacklogs(),mark.isHistoryOfArrear(),markInfo.getId());
                        studentRepository.save(student);
            }
            return student;
        }
        catch (Exception e){
            throw new Exception("student is not found"+e.getMessage());
        }


    }
    public Marks getMarks(Student student){
        try{
            return student.getMarks();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public DriveResponseDto getDriveResponse(Long driveId,Student student) throws  Exception{
        Drive drive=getDriveById(driveId);
        DriveResponseDto driveResponseDto=new DriveResponseDto();
        driveResponseDto.setDrive(drive);
        driveResponseDto.companyName=drive.getCompany().getCompanyName();
        driveResponseDto.setRegistered(drive.isStudentRegister(student));
        return driveResponseDto;
    }
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    public Student getStudentByToken(String token){
        String tokenExtraction=token.substring(7);
        String email=tokenGenerator.extractUserName(tokenExtraction);
        System.out.print("\n email "+email);
        User user=userRepository.findByEmail(email);
        return user.getStudent();
    }
    public Drive getDriveById(Long driveId) throws Exception{
        Optional<Drive> driveInfo = driverepository.findById(driveId);
        return  driveInfo.get();
    }
    public UserDetailsService userDetailsService() throws Exception {
        try{
        return username -> userRepository.findByEmail(username);}
        catch(Exception e){
            throw new Exception("student not found");
        }
    }
    public List<DriveResponseDto> getUpcomingDrive(Student student){
        List<Drive>drive=new ArrayList<>();
        drive=driverepository.findAll();
        List<DriveResponseDto>upcomingDrive=new ArrayList<>();

        for(Drive drive1:drive){
            if(drive1.getDate().after(new Date())){
                DriveResponseDto responseDto=new DriveResponseDto();
                if(drive1.getRegisteredStudents().contains(student)){
                    responseDto.setDrive(drive1);
                    responseDto.setRegistered(true);
                }
                else{

                    responseDto.setDrive(drive1);
                    responseDto.setRegistered(false);
                }
                responseDto.companyName=responseDto.getDrive().getCompany().getCompanyName();
                upcomingDrive.add(responseDto);
            }
        }
        return upcomingDrive;
    }
}




//        try{Student student=getStudentById(markDto.getStudentId());
//            if(student.getMarks()==null){
//        Marks marks=new Marks();
//        marks.setCgpa(markDto.getCgpa());
//        marks.setCurrentBacklogs(markDto.getCurrentBacklogs());
//        marks.setStudent(getStudentById(markDto.getStudentId()));
//        marks.setDiploma(markDto.getDiploma());
//        marks.setHSC1(markDto.getHSC1());
//        marks.setHSC2(markDto.getHSC2());
//        marks.setHistoryOfArrear(markDto.isHistoryOfArrear());
//        int count=0;
//        float sum=0;
//        if(markDto.getSem1()!=0){
//        marks.setSem1(markDto.getSem1());
//        sum+= markDto.getSem1();
//        count++;
//        }
//        if(markDto.getSem2()!=0){
//        marks.setSem2(markDto.getSem2());
//            sum+= markDto.getSem2();
//
//            count++;}
//        if(markDto.getSem3()!=0){
//        marks.setSem3(markDto.getSem3());
//            sum+= markDto.getSem3();
//
//            count++;}
//        if(markDto.getSem4()!=0){
//        marks.setSem4(markDto.getSem4());
//            sum+= markDto.getSem4();
//
//            count++;}
//        if(markDto.getSem5()!=0){
//        marks.setSem5(markDto.getSem5());
//            sum+= markDto.getSem5();
//
//            count++;}
//        if(markDto.getSem6()!=0){
//        marks.setSem6(markDto.getSem6());
//            sum+= markDto.getSem6();
//
//            count++;}
//        if(markDto.getSem7()!=0){
//        marks.setSem7(markDto.getSem7());
//            sum+= markDto.getSem7();
//            count++;}
//
//       float cgpa=sum/count;
//marks.setCgpa(cgpa);
//            System.out.println("marks are perfectly setted");
//            System.out.println("student is founded");
//student.setMarks(marks);
//markRepository.save(marks);
//            System.out.println("markRepository is correct");
//
//return studentRepository.save(student);}
//        else{
//        throw new Exception("student marks is already uploaded");}
//        }
//
//        catch (Exception e){
//            throw new Exception(e.getMessage()+"not found");
//        }
