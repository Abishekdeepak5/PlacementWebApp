package com.PLACEMENTWEBAPP.PlacementWebApp.Controller;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.MarkDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.*;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.CompanyService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.StudentService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/student")
public class StudentController {
  @Autowired
  private StudentService studentService;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private TokenGenerator tokenGenerator;

   @PostMapping("/registerForDrive/{driveId}")
    public BaseModel registerForDrive(@RequestHeader("Authorization")String token , @PathVariable Long driveId){
       BaseModel response=new BaseModel();
       Student student=studentService.getStudentByToken(token);
       System.out.println(student);
       try {
           DriveRegistration driveRegistration = studentService.registerForDrive(student, driveId);
           return response;
       }catch(Exception e){
           response.isSuccess=false;
           response.error.add(e.getMessage());
           return response;
       }
   }
   @PutMapping("/uploadMarks")
    public Student uploadmarks(@RequestHeader("Authorization")String token, @RequestBody Marks mark){
      try{
          Student student=studentService.getStudentByToken(token);
          return studentService.uploadMarks(student,mark);
      }catch(Exception e){
          System.out.println(e.getMessage());
          return null;
      }
   }
    @GetMapping("/getMarks")
    public Marks getMarks(@RequestHeader("Authorization")String token){
        Student student=studentService.getStudentByToken(token);
        return studentService.getMarks(student);
    }
   @GetMapping("/getUpcomingDrive")
    public List<DriveResponseDto> getUpcomingDrive(@RequestHeader("Authorization")String token){
       Student student=studentService.getStudentByToken(token);
       return studentService.getUpcomingDrive(student);
   }
   @GetMapping("/getDrive/{driveId}")
   public DriveResponseDto getDrive(@RequestHeader("Authorization")String token , @PathVariable Long driveId){
       try {
            Student student=studentService.getStudentByToken(token);
           return studentService.getDriveResponse(driveId,student);
       }catch(Exception e){
           System.out.print(e.getMessage());
           return null;
       }
   }
   @GetMapping("/getCompanies")
    public List<Company> getCompanies(@RequestHeader("Authorization")String token){
        try{
            return studentService.getCompanies();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
       return null;
   }

    @GetMapping("/getDrives/{companyId}")
    public List<DriveResponseDto> getDrives(@RequestHeader("Authorization")String token,@PathVariable Long companyId){
        try{
            Student student=studentService.getStudentByToken(token);
            return companyService.getDriveResponse(student,companyId);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
