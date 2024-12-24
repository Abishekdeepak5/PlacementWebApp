package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Company;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByCompanyName(String name);
}
