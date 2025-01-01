package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByEmail(String email);
    boolean existsByRegisterNumber(Long registerNumber);

    Student findByRegisterNumber(Long registerNumber);
    Student findByEmail(String email);
//
//    @Modifying
//    @Query("update marks_details m set m.SSLC=?1, m.HSC1=?2, m.HSC2=?3, m.diploma=?4, " +
//            "m.sem1=?5, m.sem2=?6, m.sem3=?7, m.sem4=?8, m.sem5=?8, m.sem6=?9, m.sem7=?10, " +
//            "m.cgpa=?11, m.currentBacklogs=?12, m.historyOfArrear=?13" +
//            " where m.id = ?14")
//    void setMarksById(Float SSLC,Float HSC1,Float HSC2,Float diploma,
//                      Float sem1,Float sem2,Float sem3,Float sem4,Float sem5,
//                      Float sem6,Float sem7,Float cgpa,Integer currentBacklogs,
//                      Boolean historyOfArrear,Long marks_id);
}
