package com.example.ISA.tim6.Dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CentreSaveDto {

    @NotEmpty
    @Length(min = 1)
    private String name;
    @NotEmpty
    @Length(min = 1)
    private String address;
    @NotEmpty
    @Length(min = 1)
    private String number;
    @NotEmpty
    @Length(min = 1)
    private String description;
    @Min(1)
    private double averageRate;
    @Min(1)
    private Long adminId;

    @NotEmpty
    @Length(min = 1)
    private String reserveAppointment;



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getReserveAppointment() {return reserveAppointment;}
}
