package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.CompanyRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.Driverepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriveService {
    @Autowired
    private CompanyRepository companyrepository;

    @Autowired
    private Driverepository driverepository;


    public ResponseEntity<?> createNewDrive(DriveDto driveDto) {
        Drive drive;
        try {
            drive = new Drive();
            drive.setDriveLocation(driveDto.getDriveLocation());
            drive.setDescription(driveDto.getDescription());
            drive.setCtc(driveDto.getCtc());
            drive.setEligibleCgpa(driveDto.getEligibleCgpa());
            drive.setHistoryOfAllowed(driveDto.isHistoryOfAllowed());
            drive.setJobTitle(driveDto.getJobTitle());
            drive.setDate(driveDto.getDate());
            drive.setCompanyLocation(driveDto.getCompanyLocation());

            Optional<Company> company = companyrepository.findById(driveDto.getCompanyId());
            Company company1 = company.get();
            if (company1 != null) {
                company1.setDrive(drive);
                drive.setCompany(company1);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found with ID: " + driveDto.getCompanyId());

        }
        return ResponseEntity.ok(driverepository.save(drive));

    }
}
