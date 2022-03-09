package com.example.carsmanagment.model.entitats;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Cotxe {
    @Id
    @GeneratedValue
    private Long Id;
    private String marca;
    private String model;
    private String anyCreacio;
    private String carburant;

}
