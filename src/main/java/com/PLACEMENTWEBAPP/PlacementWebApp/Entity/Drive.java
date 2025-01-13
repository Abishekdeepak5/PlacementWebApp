package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="drive")
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private Date RegistrationClosingDate;
    private String jobTitle;
    private double eligibleCgpa;
    private String description;
    private int count;
    @ManyToMany
    @JoinTable(
            name = "drive_student_registration",
            joinColumns = @JoinColumn(name = "drive_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private List<Student> registeredStudents;

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Date getRegistrationClosingDate() {
        return RegistrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate) {
        RegistrationClosingDate = registrationClosingDate;
    }

    private boolean isHistoryOfAllowed;
    private double ctc;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public double getEligibleCgpa() {
        return eligibleCgpa;
    }

    public void setEligibleCgpa(Double eligibleCgpa) {
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

    public double getCtc() {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isStudentRegister(Student student){
        return registeredStudents.contains(student);
    }
    public boolean isClosed(){
        return this.RegistrationClosingDate.before(this.date);
    }
    public boolean isDriveFinish(){
        return this.date.before(new Date());
    }

    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                ", date=" + date +
                ", RegistrationClosingDate=" + RegistrationClosingDate +
                ", jobTitle='" + jobTitle + '\'' +
                ", eligibleCgpa=" + eligibleCgpa +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", registeredStudents=" + registeredStudents +
                ", isHistoryOfAllowed=" + isHistoryOfAllowed +
                ", ctc=" + ctc +
                ", companyLocation='" + companyLocation + '\'' +
                ", driveLocation='" + driveLocation + '\'' +
                ", company=" + company +
                '}';
    }
}
