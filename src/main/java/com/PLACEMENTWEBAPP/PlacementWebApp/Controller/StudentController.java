package com.PLACEMENTWEBAPP.PlacementWebApp.Controller;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.MarkDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.DriveRegistration;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Marks;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.StudentService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

@RestController
@RequestMapping("/api/student")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @Autowired
  private TokenGenerator tokenGenerator;

   @PostMapping("/registerForDrive/{studentId}/{driveId}")
    public DriveRegistration registerForDrive(@PathVariable Long studentId,@PathVariable Long driveId) throws Exception {
      DriveRegistration driveRegistration=studentService.registerForDrive(studentId, driveId);
       if(driveRegistration!=null){
           return driveRegistration;
       }
       else{
           throw new Exception("something happens");
       }
   }
   @PutMapping("/uploadMarks")
    public Student uploadmarks(@RequestHeader("Authorization")String token, @RequestBody Marks mark) throws Exception {
       String tokenExtraction=token.substring(7);
       String email=tokenGenerator.extractUserName(tokenExtraction);
       System.out.println("welcome");
       return studentService.uploadMarks(email,mark);
   }

}
