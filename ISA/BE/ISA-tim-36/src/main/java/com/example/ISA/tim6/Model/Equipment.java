package com.example.ISA.tim6.Model;

import javax.persistence.*;

@Entity(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    public Equipment() {
    }

    public Equipment(String name, Integer stockQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
