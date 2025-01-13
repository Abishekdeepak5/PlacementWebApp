package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.DriveResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.CompanyRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.QueryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private QueryRepo queryRepo;
    public Company createNewCompany(Company company) throws Exception {
        try{
        if(!companyRepository.existsByCompanyName(company.getCompanyName())){
            return companyRepository.save(company);}
        }
        catch (Exception e){
            throw new Exception("err "+e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        throw new Exception("already available company");

    }
    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }
    public Set<Drive> getDrives(Long companyId){
        Company company=companyRepository.getById(companyId);
        return company.getDrive();
    }
    public List<DriveResponseDto> getDriveResponse(Student student, Long companyId){
        Set<Drive> drives=getDrives(companyId);
        List<DriveResponseDto> driveRepo=new ArrayList<>();
        for(Drive drive:drives){
            DriveResponseDto driverepo=new DriveResponseDto();
            driverepo.setDrive(drive);
            driverepo.setRegistered(drive.getRegisteredStudents().contains(student));
            driverepo.companyName=drive.getCompany().getCompanyName();
            driveRepo.add(driverepo);
        }
        return driveRepo;
    }
    public void deleteCompany(Long companyId){
            Set<Drive> drives=getDrives(companyId);
            for(Drive drive:drives){
                queryRepo.deleteDrive(Long.valueOf(drive.getId()));
            }
            queryRepo.deleteDrive(companyId);
            queryRepo.deleteCompany(companyId);
    }
}
