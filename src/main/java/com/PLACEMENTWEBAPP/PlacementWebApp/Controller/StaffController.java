package com.PLACEMENTWEBAPP.PlacementWebApp.Controller;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Staff;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.CompanyRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.StaffRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.CompanyService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.DriveService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DriveService driveService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;



    @PostMapping("/createCompany")
    public ResponseEntity<?> createCompany(@RequestBody Company company) throws Exception {
        try {
            staffService.createNewCompany(company);
            return  ResponseEntity.ok("company is created");
        }
    catch(Exception e){
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }
    @GetMapping("/getCompany/{id}")
    public ResponseEntity<?> getCompanyId(@RequestParam Long id){
     try{return  ResponseEntity.ok(companyRepository.findById(id).get());}
     catch (Exception e){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found with ID: " + id);
     }
    }
    @PostMapping("/createDrive")
    public ResponseEntity<?> createNewDrive(@RequestBody DriveDto driveDto){
        try{
            return ResponseEntity.ok(staffService.createNewDrive(driveDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
