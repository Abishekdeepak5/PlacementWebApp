package com.PLACEMENTWEBAPP.PlacementWebApp.Controller;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.DriveRegistration;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
  @Autowired
  private StudentService studentService;

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
}
