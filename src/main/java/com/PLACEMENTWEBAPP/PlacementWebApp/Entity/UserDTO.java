package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class UserDTO extends BaseModel{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    public String name;
    public Date dateOfBirth;
    public String email;
    public char gender;
    public String department;
    public String password;
    public int otp;
    public Role role;
    public boolean verified;
    public String phone;
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department='" + department + '\'' +
                ", password='" + password + '\'' +
                ", otp=" + otp +
                ", role=" + role +
                ", verified=" + verified +
                ", UserId=" + UserId +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", token='" + token + '\'' +
                ", isSuccess=" + isSuccess +'\''+
                ",phone="+phone+
                '}';
    }
}
