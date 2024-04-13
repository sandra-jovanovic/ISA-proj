package com.example.ISA.tim6.Model;

import com.example.ISA.tim6.ENUMS.LoyalityProgramName;

import javax.persistence.*;

public class LoyalityProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "loyalityProgramName")
    @Enumerated(EnumType.STRING)
    private LoyalityProgramName loyalityProgramName;

    @Column(name = "amountOfPoints")
    private int amountOfPoints;
}
