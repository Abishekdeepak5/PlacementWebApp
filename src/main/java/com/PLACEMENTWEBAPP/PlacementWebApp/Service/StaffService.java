package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private DriveService driveService;

    @Autowired
    private CompanyService companyService;

    public ResponseEntity<?> createNewDrive(DriveDto driveDto) {
        try {
            return ResponseEntity.ok(driveService.createNewDrive(driveDto));
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drive ID: " + driveDto.getCompanyId());

        }
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