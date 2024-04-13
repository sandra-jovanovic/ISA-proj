package com.example.ISA.tim6.Model;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "centre_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_center_complaint")
    )
    private Centre centre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "medical_worker_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_User_Complaint_Medical_Worker")
    )
    private User medicalWorker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_User_Complaint")
    )
    private User user;

    @Column(name = "description")
    private  String description;

    @Column(name = "answer")
    private  String answer;

    public Complaint() {
    }

    public Complaint(String description, String answer) {
        this.description = description;
        this.answer = answer;
    }

    public void setUser(User _user) {
        this.user = _user;
    }

    public void setMedicalWorker(User _user) {
        this.medicalWorker = _user;
    }

    public long getId() {
        return id;
    }

    public User getMedicalWorker() {
        return medicalWorker;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCentre(Centre _centre) {
        this.centre = _centre;
    }

    public Centre getCentre() {
        return this.centre;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
