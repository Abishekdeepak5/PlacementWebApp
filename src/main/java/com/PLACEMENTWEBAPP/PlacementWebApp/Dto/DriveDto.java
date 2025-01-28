package com.PLACEMENTWEBAPP.PlacementWebApp.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriveDto {
    public Long id;
    public Date registrationClosingDate;
    private Date date;
    private String jobTitle;
    private Double eligibleCgpa;
    private String description;
    private boolean isHistoryOfAllowed;
    private Double ctc;
    private String companyLocation;
    private String driveLocation;
    private Long companyId;
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

    public Double getEligibleCgpa() {
        return eligibleCgpa;
    }

    public void setEligibleCgpa(Double eligibleCgpa) {
        this.eligibleCgpa = eligibleCgpa;
    }

    public boolean isHistoryOfAllowed() {
        return isHistoryOfAllowed;
    }

    public void setHistoryOfAllowed(boolean historyOfAllowed) {
        isHistoryOfAllowed = historyOfAllowed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
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

    public Long getCompanyId() {
        return companyId;
    }

    public Date getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setGetRegistrationClosingDate(Date getRegistrationClosingDate) {
        this.registrationClosingDate = getRegistrationClosingDate;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "DriveDto{" +
                "id=" + id +
                ", RegistrationClosingDate=" + registrationClosingDate +
                ", date=" + date +
                ", jobTitle='" + jobTitle + '\'' +
                ", eligibleCgpa=" + eligibleCgpa +
                ", description='" + description + '\'' +
                ", isHistoryOfAllowed=" + isHistoryOfAllowed +
                ", ctc=" + ctc +
                ", companyLocation='" + companyLocation + '\'' +
                ", driveLocation='" + driveLocation + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
