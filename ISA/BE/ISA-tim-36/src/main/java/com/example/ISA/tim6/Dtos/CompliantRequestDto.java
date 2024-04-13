package com.example.ISA.tim6.Dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompliantRequestDto {

    @NotNull
    private Long complaintId;
    @NotBlank
    private String answer;

    public CompliantRequestDto() {
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
