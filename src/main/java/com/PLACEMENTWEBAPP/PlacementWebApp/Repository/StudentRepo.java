package com.PLACEMENTWEBAPP.PlacementWebApp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepo {

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
    }

}
