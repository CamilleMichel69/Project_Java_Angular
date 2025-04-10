package com.example.diningreviews.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.diningreviews.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByDisplayName(String displayName);
}