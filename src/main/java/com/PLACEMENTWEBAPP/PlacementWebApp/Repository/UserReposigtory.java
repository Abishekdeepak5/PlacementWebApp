package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposigtory  extends JpaRepository<Student,Long> {

}
