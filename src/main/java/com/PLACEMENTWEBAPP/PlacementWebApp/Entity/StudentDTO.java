package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends BaseModel{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    public Long registerNumber;
    public String rollNumber;
    public int batch;
    public boolean hosteler;
    public boolean isPlaced;
    public Role role;
    public UserDTO user;

    public User getUser(){
        User user=new User();
        user.setEmail(this.user.email);
        user.setDepartment(this.user.department);
        user.setGender(this.user.gender);
        user.setName(this.user.name);
        user.setDateOfBirth(this.user.dateOfBirth);
        user.setPassword(this.user.password);
        user.setRole(this.user.role);
        user.setVerified(this.user.verified);
        user.setOtp(this.user.otp);
        user.setPhone(this.user.phone);
        return user;
    }
    public Student getStudentDetail(){
        Student student = new Student();
        student.setPlaced(this.isPlaced);
        student.setBatch(this.batch);
        student.setHosteler(this.hosteler);
        student.setRegisterNumber(this.registerNumber);
        student.setRollNumber(this.rollNumber);
        return student;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", registerNumber=" + registerNumber +
                ", rollNumber='" + rollNumber + '\'' +
                ", batch=" + batch +
                ", hosteler=" + hosteler +
                ", isPlaced=" + isPlaced +
                ", user=" + user +
                '}';
    }
}