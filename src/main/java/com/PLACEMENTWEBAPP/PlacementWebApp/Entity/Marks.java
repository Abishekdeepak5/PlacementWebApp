package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="marksDetails")
public class Marks {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private float SSLC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSSLC() {
        return SSLC;
    }

    public void setSSLC(float SSLC) {
        this.SSLC = SSLC;
    }

    public float getHSC1() {
        return HSC1;
    }

    public void setHSC1(float HSC1) {
        this.HSC1 = HSC1;
    }

    public float getHSC2() {
        return HSC2;
    }

    public void setHSC2(float HSC2) {
        this.HSC2 = HSC2;
    }

    public float getDiploma() {
        return diploma;
    }

    public void setDiploma(float diploma) {
        this.diploma = diploma;
    }

    public float getSem1() {
        return sem1;
    }

    public void setSem1(float sem1) {
        this.sem1 = sem1;
    }

    public float getSem2() {
        return sem2;
    }

    public void setSem2(float sem2) {
        this.sem2 = sem2;
    }

    public float getSem3() {
        return sem3;
    }

    public void setSem3(float sem3) {
        this.sem3 = sem3;
    }

    public float getSem4() {
        return sem4;
    }

    public void setSem4(float sem4) {
        this.sem4 = sem4;
    }

    public float getSem5() {
        return sem5;
    }

    public void setSem5(float sem5) {
        this.sem5 = sem5;
    }

    public float getSem6() {
        return sem6;
    }

    public void setSem6(float sem6) {
        this.sem6 = sem6;
    }

    public float getSem7() {
        return sem7;
    }

    public void setSem7(float sem7) {
        this.sem7 = sem7;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public boolean isHistoryOfArrear() {
        return historyOfArrear;
    }

    public void setHistoryOfArrear(boolean historyOfArrear) {
        this.historyOfArrear = historyOfArrear;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCurrentBacklogs() {
        return currentBacklogs;
    }

    public void setCurrentBacklogs(int currentBacklogs) {
        this.currentBacklogs = currentBacklogs;
    }

    private float HSC1;
    private float HSC2;
    private float diploma;
    private float sem1;
    private float sem2;
    private float sem3;
    private float sem4;
    private float sem5;
    private float sem6;
    private float sem7;
    private float cgpa;
    private boolean historyOfArrear;
    private int currentBacklogs;

    @OneToOne(mappedBy = "marks")
    @JsonIgnore
    private Student student;

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", SSLC=" + SSLC +
                ", HSC1=" + HSC1 +
                ", HSC2=" + HSC2 +
                ", diploma=" + diploma +
                ", sem1=" + sem1 +
                ", sem2=" + sem2 +
                ", sem3=" + sem3 +
                ", sem4=" + sem4 +
                ", sem5=" + sem5 +
                ", sem6=" + sem6 +
                ", sem7=" + sem7 +
                ", cgpa=" + cgpa +
                ", historyOfArrear=" + historyOfArrear +
                ", currentBacklogs=" + currentBacklogs +
                ", student=" + student +
                '}';
    }
}
