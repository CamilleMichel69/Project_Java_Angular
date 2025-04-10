package com.example.diningreviews.models;

import jakarta.persistence.*;

@Entity
public class DiningReviews {

    @Id
    @GeneratedValue
    private Long id;

    private String submittedBy;
    private Long restaurantId;
    private String review;

    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;

    private ReviewStatus status;

    public DiningReviews() {
    }

    public DiningReviews(String submittedBy, Long restaurantId, String review, Double peanutScore, Double eggScore, Double dairyScore, ReviewStatus status) {
        this.submittedBy = submittedBy;
        this.restaurantId = restaurantId;
        this.review = review;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }
}
