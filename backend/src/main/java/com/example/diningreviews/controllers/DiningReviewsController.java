package com.example.diningreviews.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.diningreviews.models.DiningReviews;
import com.example.diningreviews.models.ReviewStatus;
import com.example.diningreviews.repositories.DiningReviewsRepository;
import com.example.diningreviews.repositories.RestaurantRepository;
import com.example.diningreviews.repositories.UserRepository;

@RequestMapping("/reviews")
@RestController
public class DiningReviewsController {
    private final DiningReviewsRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewsController(DiningReviewsRepository diningReviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DiningReviews submitReview(@RequestBody DiningReviews review) {
    	
    	if (!restaurantRepository.existsById(review.getRestaurantId())) {
    		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Restaurant inconnu.");
    	}
    	
    	if (!userRepository.findByDisplayName(review.getSubmittedBy()).isPresent()) {
    		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Utilisateur inconnu.");
    	}
    	
    	if (review.getReview() == null || review.getReview().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Merci d'indiquer un commentaire.");
        }

    	validateScore(review.getPeanutScore(), "Peanut");
        validateScore(review.getEggScore(), "Egg");
        validateScore(review.getDairyScore(), "Dairy");
    	
    	review.setStatus(ReviewStatus.PENDING);
    	return diningReviewRepository.save(review);
    }
    
    @GetMapping
    public Iterable<DiningReviews> getAllReviews() {
        return diningReviewRepository.findAll();
    }
    
    private void validateScore(Double score, String allergyType) {
        if (score != null) {
            if (score > 5) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, allergyType + " score ne peut pas être supérieur à 5.");
            }
            if (score % 1 != 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, allergyType + " score doit être un nombre entier.");
            }
        }
    }
}
