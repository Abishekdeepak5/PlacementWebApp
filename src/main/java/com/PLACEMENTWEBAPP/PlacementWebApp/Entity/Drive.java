package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="drive")
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String jobTitle;
    private Long eligibleCgpa;
    private String description;
    private boolean isHistoryOfAllowed;
    private Long ctc;
    private String companyLocation;
    private String driveLocation;
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.PERSIST)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getEligibleCgpa() {
        return eligibleCgpa;
    }

    public void setEligibleCgpa(Long eligibleCgpa) {
        this.eligibleCgpa = eligibleCgpa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHistoryOfAllowed() {
        return isHistoryOfAllowed;
    }

    public void setHistoryOfAllowed(boolean historyOfAllowed) {
        isHistoryOfAllowed = historyOfAllowed;
    }

    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getDriveLocation() {
        return driveLocation;
    }

    public void setDriveLocation(String driveLocation) {
        this.driveLocation = driveLocation;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
