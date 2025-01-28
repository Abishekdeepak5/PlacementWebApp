package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user_detail")
@NoArgsConstructor
@AllArgsConstructor
public class User  extends BaseModel implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Date dateOfBirth;
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Boolean verified;
    @NotNull
    private Character gender;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    private Integer otp;

    private String department;
    private String phone;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role.name());    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setStudent(Student student){
        this.student=student;
    }
    public Student getStudent(){
        return this.student;
    }
    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department =department;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public void setOtp(int otp){
        this.otp=otp;
    }
    public  Integer getOtp(){
        return this.otp;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public void setPhone(String phone){ this.phone=phone; }
    public String getPhone(){ return this.phone; }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verified=" + verified +
                ", gender=" + gender +
                ", role=" + role +
                ", otp=" + otp +
                ", department='" + department + '\'' +
                ", student=" + student +
                ", UserId=" + UserId +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", token='" + token + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
