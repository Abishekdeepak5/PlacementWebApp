package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
