package com.example.ISA.tim6.Model;

import com.example.ISA.tim6.ENUMS.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "average_rate")
    private double averageRate;

    @JsonIgnore
    @OneToMany(
            mappedBy = "centre",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Complaint> complaints;


    @JsonIgnore
    @ManyToMany
    @Column(name="admin")
    List<User> admins;

    public Centre() {
    }

    public Centre(String name, String streetName, String streetNumber, String description, double averageRate, List<User> admins) {
        this.name = name;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.description = description;
        this.averageRate = averageRate;
        this.admins = admins;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public void addComplaint(Complaint complaint) {
        if (!complaints.contains(complaint)) {
            complaints.add(complaint);
            complaint.setCentre(this);
        }
    }

}
