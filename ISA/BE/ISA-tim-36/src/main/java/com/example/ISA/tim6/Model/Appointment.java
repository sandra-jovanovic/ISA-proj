package com.example.ISA.tim6.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "appointments")

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "medical_worker_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_User_Appointment_Medical_Worker")
    )
    private User medicalWorker;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "durationInMinutes")
    private int durationInMinutes;

    @Column(name = "info")
    private String info;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_User_Appointment")
    )
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "centre_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_center_appointment")
    )
    private Centre centre;

    @Column(name = "did_not_show")
    private boolean didNotShow;
    @Column(name = "conditions_not_fullfiled")
    private boolean conditionsNotFulfilled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(User medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public boolean isDidNotShow() {
        return didNotShow;
    }

    public void setDidNotShow(boolean didNotShow) {
        this.didNotShow = didNotShow;
    }

    public boolean isConditionsNotFulfilled() {
        return conditionsNotFulfilled;
    }

    public void setConditionsNotFulfilled(boolean conditionsNotFulfilled) {
        this.conditionsNotFulfilled = conditionsNotFulfilled;
    }

    public boolean isAvailableInThatTime(LocalDateTime dateTime)
    {
        return  getDateTime().isBefore(dateTime) && getDateTime().plusMinutes(getDurationInMinutes()).isAfter(dateTime);
    }
}
