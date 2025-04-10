package com.example.diningreviews.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    private String displayName;
    private String city;
    private String zipCode;

    private Boolean isPeanutAllergy;
    private Boolean isEggAllergy;
    private Boolean isDairyAllergy;

    public User() {
    }

    public User(String displayName, String city, String zipCode, Boolean isPeanutAllergy, Boolean isEggAllergy, Boolean isDairyAllergy) {
        this.displayName = displayName;
        this.city = city;
        this.zipCode = zipCode;
        this.isPeanutAllergy = isPeanutAllergy;
        this.isEggAllergy = isEggAllergy;
        this.isDairyAllergy = isDairyAllergy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public Boolean getIsPeanutAllergy() {
        return isPeanutAllergy;
    }

    public void setIsPeanutAllergy(Boolean isPeanutAllergy) {
        this.isPeanutAllergy = isPeanutAllergy;
    }

    public Boolean getIsEggAllergy() {
        return isEggAllergy;
    }

    public void setIsEggAllergy(Boolean isEggAllergy) {
        this.isEggAllergy = isEggAllergy;
    }

    public Boolean getIsDairyAllergy() {
        return isDairyAllergy;
    }

    public void setIsDairyAllergy(Boolean isDairyAllergy) {
        this.isDairyAllergy = isDairyAllergy;
    }
}
