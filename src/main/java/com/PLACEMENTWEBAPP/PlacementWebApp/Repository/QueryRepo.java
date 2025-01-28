package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class QueryRepo {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setMarksById(Float SSLC, Float HSC1, Float HSC2, Float diploma,
                             Float sem1, Float sem2, Float sem3, Float sem4, Float sem5,
                             Float sem6, Float sem7, Float cgpa, Integer currentBacklogs,
                             Boolean historyOfArrear, Long marksId) {

        String sql = "UPDATE marks_details " +
                "SET SSLC = ?, HSC1 = ?, HSC2 = ?, diploma = ?, " +
                "sem1 = ?, sem2 = ?, sem3 = ?, sem4 = ?, sem5 = ?, " +
                "sem6 = ?, sem7 = ?, cgpa = ?, current_Backlogs = ?, history_Of_Arrear = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, SSLC, HSC1, HSC2, diploma, sem1, sem2, sem3, sem4, sem5,
                sem6, sem7, cgpa, currentBacklogs, historyOfArrear, marksId);
        entityManager.clear();
    }
    public void setDriveById(String companyLoc, Double ctc, Date date, String desc,
                            String driveLoc , Double cgpa, Boolean isHistory, String job,
                             Date regClose, Long driveId) {
        String sql = "UPDATE drive " +
                "SET company_location = ?, ctc = ?,  description = ?, " +
                "drive_location = ?, eligible_cgpa = ?, is_history_of_allowed = ?, job_title = ?, " +
                " date=?, registration_closing_date=? "+
                "WHERE id = ?";

        jdbcTemplate.update(sql, companyLoc,ctc,desc,driveLoc,cgpa,isHistory,job,new Timestamp(date.getTime()), new Timestamp(regClose.getTime()),driveId);
        entityManager.clear();
    }
    public void deleteCompany(Long companyId){
        String sql = "delete from company where id=?";
        jdbcTemplate.update(sql,companyId);
        entityManager.clear();
    }
    public void deleteDrive(Long driveId){
        deleteDriveRegistration(driveId);
        deleteDriveStudentRegistration(driveId);
        String sql = "delete from drive where id=?";
        jdbcTemplate.update(sql,driveId);
        entityManager.clear();
    }
    public void deleteDriveRegistration(Long driveId){
        String sql = "delete from drive_registration where drive_id=?";
        jdbcTemplate.update(sql,driveId);
        entityManager.clear();
    }
    public void deleteDriveStudentRegistration(Long driveId){
        String sql = "delete from drive_student_registration where drive_id=?";
        jdbcTemplate.update(sql,driveId);
        entityManager.clear();
    }
    private String formatDate(Date dateObj){
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(dateObj);
        return formattedDate;
    }
}
