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
@Table(name="student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseModel{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long registerNumber;

    @NotNull
    private Integer batch;

    @NotNull
    private Boolean hosteler;

    @NotNull
    private String rollNumber;

    @ManyToMany(mappedBy = "registeredStudents")
    private List<Drive>registeredDriveList;

    private Boolean isPlaced;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="marks_id" ,referencedColumnName="id")
    private Marks marks;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id" ,referencedColumnName="id")
    private User user;



    public void setRegisteredDriveList(List<Drive> registeredDriveList) {
        this.registeredDriveList = registeredDriveList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Integer getBatch() {
        return batch;
    }

    public Boolean isHosteler() {
        return hosteler;
    }

    public List<Drive> getRegisteredDriveList() {
        return registeredDriveList;
    }

    public void setHosteler(boolean hosteler) {
        this.hosteler = hosteler;
    }


    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser(){return this.user;}
}
