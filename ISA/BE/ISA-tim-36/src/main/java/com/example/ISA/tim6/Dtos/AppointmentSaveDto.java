package com.example.ISA.tim6.Dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentSaveDto {

    @NotNull
    private LocalDateTime dateAndTime;

    @NotNull
    private int duration;

    @NotNull
    private Long medicalWorker;

    @NotNull
    private Long centre;

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(Long medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public Long getCentre() {
        return centre;
    }

    public void setCentre(Long centre) {
        this.centre = centre;
    }
}
