package com.example.diningreviews.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.diningreviews.models.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
	boolean existsByNameAndZipCode(String name, String zipCode);
	Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
	List<Restaurant> findByZipCodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndEggScoreIsNotNullOrderByEggScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(String zipCode);

}