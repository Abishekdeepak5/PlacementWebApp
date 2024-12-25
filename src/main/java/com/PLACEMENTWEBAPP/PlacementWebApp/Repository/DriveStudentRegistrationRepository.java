package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.DriveRegistration;
public interface DriveStudentRegistrationRepository extends JpaRepository<DriveRegistration,Long> {
}
