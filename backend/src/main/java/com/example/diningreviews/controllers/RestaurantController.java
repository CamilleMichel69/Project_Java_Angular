package com.example.diningreviews.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.diningreviews.models.Restaurant;
import com.example.diningreviews.repositories.RestaurantRepository;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private static final String ZIP_CODE_REGEX = "^[0-9]{5}$";

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
    	if (restaurant.getName() == null || restaurant.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom du restaurant est obligatoire.");
        }
        if (restaurant.getAddress() == null || restaurant.getAddress().isBlank()) {
            throw new IllegalArgumentException("L'adresse du restaurant est obligatoire.");
        }
        if (restaurant.getCity() == null || restaurant.getCity().isBlank()) {
            throw new IllegalArgumentException("La ville du restaurant est obligatoire.");
        }

        if (restaurant.getZipCode() == null || restaurant.getZipCode().isBlank()) {
            throw new IllegalArgumentException("Le code postal est obligatoire.");
        }

        if (!restaurant.getZipCode().matches(ZIP_CODE_REGEX)) {
            throw new IllegalArgumentException("Le code postal doit être exactement composé de 5 chiffres.");
        }

        if (restaurantRepository.existsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode())) {
            throw new IllegalArgumentException("Un restaurant avec le même nom et code postal existe déjà.");
        }
        
        if (restaurant.getPeanutScore() != null || restaurant.getEggScore() != null || restaurant.getDairyScore() != null) {
            throw new IllegalArgumentException("Aucun score d'allergie ne peut être associé lors de la création d'un restaurant.");
        }

        return restaurantRepository.save(restaurant);
    }
    
    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Restaurant non trouvé pour l'ID " + id));
    }

    @GetMapping("/search")
    public List<Restaurant> getRestaurantsByZipCodeAndAllergyScore(
            @RequestParam String zipCode,
            @RequestParam String allergy) {
        
        if (!zipCode.matches(ZIP_CODE_REGEX)) {
            throw new IllegalArgumentException("Le code postal doit être exactement composé de 5 chiffres.");
        }

        switch (allergy.toLowerCase()) {
            case "peanut":
                return restaurantRepository.findByZipCodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(zipCode);
            case "egg":
                return restaurantRepository.findByZipCodeAndEggScoreIsNotNullOrderByEggScoreDesc(zipCode);
            case "dairy":
                return restaurantRepository.findByZipCodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(zipCode);
            default:
                throw new IllegalArgumentException("Allergie non valide, veuillez spécifier 'peanut', 'egg' ou 'dairy'.");
        }
    }   
}
