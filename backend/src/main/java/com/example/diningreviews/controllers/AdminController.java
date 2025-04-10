package com.example.diningreviews.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreviews.models.AdminReviewAction;
import com.example.diningreviews.models.DiningReviews;
import com.example.diningreviews.models.Restaurant;
import com.example.diningreviews.models.ReviewStatus;
import com.example.diningreviews.repositories.DiningReviewsRepository;
import com.example.diningreviews.repositories.RestaurantRepository;

@RequestMapping("/admin")
@RestController
public class AdminController {
    private final DiningReviewsRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    private final DecimalFormat df = new DecimalFormat("0.00");

    public AdminController(DiningReviewsRepository diningReviewRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public List<DiningReviews> getPendingReviews() {
        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PutMapping("/reviews/{reviewId}")
    public DiningReviews updateReviewStatus(@PathVariable Long reviewId, @RequestBody AdminReviewAction action) {
        Optional<DiningReviews> optionalReview = diningReviewRepository.findById(reviewId);

        if (optionalReview.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avis non trouvé.");
        }

        DiningReviews review = optionalReview.get();
        
        review.setStatus(action.getAccept() ? ReviewStatus.ACCEPTED : ReviewStatus.REJECTED);
        diningReviewRepository.save(review);

        if (action.getAccept()) {
            calculateRestaurantScores(review.getRestaurantId());
        }

        return review;
    }
    
    private void calculateRestaurantScores(Long restaurantId) {
        List<DiningReviews> approvedReviews = diningReviewRepository.findByRestaurantIdAndStatus(restaurantId, ReviewStatus.ACCEPTED);

        if (approvedReviews.isEmpty()) {
            return;
        }

        double peanutTotal = 0, eggTotal = 0, dairyTotal = 0;
        int peanutCount = 0, eggCount = 0, dairyCount = 0;

        for (DiningReviews review : approvedReviews) {
            if (review.getPeanutScore() != null) {
                peanutTotal += review.getPeanutScore();
                peanutCount++;
            }
            if (review.getEggScore() != null) {
                eggTotal += review.getEggScore();
                eggCount++;
            }
            if (review.getDairyScore() != null) {
                dairyTotal += review.getDairyScore();
                dairyCount++;
            }
        }

        double avgPeanut = peanutCount > 0 ? peanutTotal / peanutCount : 0;
        double avgEgg = eggCount > 0 ? eggTotal / eggCount : 0;
        double avgDairy = dairyCount > 0 ? dairyTotal / dairyCount : 0;

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setPeanutScore(formatScore(avgPeanut));
            restaurant.setEggScore(formatScore(avgEgg));
            restaurant.setDairyScore(formatScore(avgDairy));

            restaurantRepository.save(restaurant);
        }
    }
    
    private double formatScore(double score) {
        return Double.parseDouble(df.format(score).replace(",", "."));
    }

}