package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByRegisterNumber(Long registerNumber);
    Student findByUserId(Long user_id);
    Student findByRegisterNumber(Long registerNumber);

    @Query(nativeQuery = true,value = "select * from student  where user_id=?1")
    Student findByUserID(Long id);
}
