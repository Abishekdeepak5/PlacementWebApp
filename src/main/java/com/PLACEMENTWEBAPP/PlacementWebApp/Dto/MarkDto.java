package com.PLACEMENTWEBAPP.PlacementWebApp.Dto;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Marks;

public class MarkDto extends Marks {
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
