package com.example.diningreviews.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.example.diningreviews.models.DiningReviews;
import com.example.diningreviews.models.ReviewStatus;

public interface DiningReviewsRepository extends CrudRepository<DiningReviews, Long> {
	List<DiningReviews> findByStatus(ReviewStatus reviewStatus);
	List<DiningReviews> findByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);
}