package com.example.ISA.tim6.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //1-5
    @Column(name = "rating")
    private int rating;

    @Column(name = "userId")
    private long userId;

    @Column(name = "centerId")
    private long centerId;



}
