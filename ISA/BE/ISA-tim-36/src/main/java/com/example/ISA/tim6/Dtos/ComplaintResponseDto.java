package com.example.ISA.tim6.Dtos;

public class ComplaintResponseDto {
    Long id;
    Long centerId;
    String centerName;
    Long medicalWorkerId;
    String medicalWorkerFirstName;
    String medicalWorkerLastName;
    Long userId;
    String userFirstName;
    String userLastName;
    String description;
    String answer;

    public ComplaintResponseDto() {
    }

    public ComplaintResponseDto(Long id, Long centerId, String centerName, Long medicalWorkerId, String medicalWorkerFirstName, String medicalWorkerLastName, Long userId, String userFirstName, String userLastName, String description, String answer) {
        this.id = id;
        this.centerId = centerId;
        this.centerName = centerName;
        this.medicalWorkerId = medicalWorkerId;
        this.medicalWorkerFirstName = medicalWorkerFirstName;
        this.medicalWorkerLastName = medicalWorkerLastName;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.description = description;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Long getMedicalWorkerId() {
        return medicalWorkerId;
    }

    public void setMedicalWorkerId(Long medicalWorkerId) {
        this.medicalWorkerId = medicalWorkerId;
    }

    public String getMedicalWorkerFirstName() {
        return medicalWorkerFirstName;
    }

    public void setMedicalWorkerFirstName(String medicalWorkerFirstName) {
        this.medicalWorkerFirstName = medicalWorkerFirstName;
    }

    public String getMedicalWorkerLastName() {
        return medicalWorkerLastName;
    }

    public void setMedicalWorkerLastName(String medicalWorkerLastName) {
        this.medicalWorkerLastName = medicalWorkerLastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
