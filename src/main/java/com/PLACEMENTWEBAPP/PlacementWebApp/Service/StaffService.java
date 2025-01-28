package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private DriveService driveService;
    @Autowired
    private QueryRepo queryRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StudentService studentService;

    public  ResponseEntity<?> createNewDrive(DriveDto driveDto) {
        try{
            System.out.println(driveDto);
            if(driveDto.id==0){
                return ResponseEntity.ok(driveService.createNewDrive(driveDto));
            }
            Drive drive =studentService.getDriveById(driveDto.id);
            queryRepo.setDriveById(driveDto.getCompanyLocation(),driveDto.getCtc(),driveDto.getDate(),
                        driveDto.getDescription(),driveDto.getDriveLocation(),driveDto.getEligibleCgpa(),
                        driveDto.isHistoryOfAllowed(), driveDto.getJobTitle(),driveDto.getRegistrationClosingDate(),Long.valueOf(drive.getId()));
            return ResponseEntity.ok(driveDto);
        }
        catch (Exception e){
            System.out.println("student is not found"+e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drive ID: " + driveDto.getCompanyId());
    }
    public Company createNewCompany(Company company) throws Exception {
        try {
            return companyService.createNewCompany(company);
        }
        catch (Exception e) {
            throw  new Exception(e.getMessage());

        }
    }
}