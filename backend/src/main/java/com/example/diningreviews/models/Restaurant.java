package com.example.diningreviews.models;

import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private String city;
    private String zipCode;

    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String city, String zipCode, Double peanutScore, Double eggScore, Double dairyScore) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getPeanutScore() {
        return peanutScore;
    }

    public void setPeanutScore(Double peanutScore) {
        this.peanutScore = peanutScore;
    }

    public Double getEggScore() {
        return eggScore;
    }

    public void setEggScore(Double eggScore) {
        this.eggScore = eggScore;
    }

    public Double getDairyScore() {
        return dairyScore;
    }

    public void setDairyScore(Double dairyScore) {
        this.dairyScore = dairyScore;
    }
}