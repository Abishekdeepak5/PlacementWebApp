package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    public Company createNewCompany(Company company) throws Exception {
        try{
        if(!companyRepository.existsByCompanyName(company.getCompanyName())){

            return companyRepository.save(company);}
        }
        catch (Exception e){
            throw new Exception("company already exists");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        throw new Exception("already available company");

    }
    public void deleteCompany(Long id) throws Exception {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        }
        else{
            throw new Exception("company not found");
        }
    }
}
