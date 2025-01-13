package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.CompanyRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.Driverepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriveService {
    @Autowired
    private CompanyRepository companyrepository;

    @Autowired
    private Driverepository driverepository;
    @Autowired
    QueryRepo queryRepo;

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
            drive.setCount(0);
            if(driveDto.getRegistrationClosingDate().before(driveDto.getDate()) ){
                drive.setRegistrationClosingDate(driveDto.getRegistrationClosingDate());
            }
            else{
                throw new RuntimeException("date is not correct");
            }
            drive.setCompanyLocation(driveDto.getCompanyLocation());
            Optional<Company> company = companyrepository.findById(driveDto.getCompanyId());
            Company company1 = company.get();
            if (company1 != null) {
                company1.setDrive(drive);
                drive.setCompany(company1);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(driverepository.save(drive));
    }
    public void deleteDrive(Long driveId) throws Exception{
        queryRepo.deleteDrive(driveId);
    }
    public List<Student> getDriveStudent(Long driveId){
        Optional<Drive> driveOp=driverepository.findById(driveId);
        Drive drive=driveOp.get();
        return drive.getRegisteredStudents();
    }
}
