package com.example.diningreviews.models;

public class AdminReviewAction {
    private Boolean accept;

    public AdminReviewAction() {
    }

    public AdminReviewAction(Boolean accept) {
        this.accept = accept;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }
}
