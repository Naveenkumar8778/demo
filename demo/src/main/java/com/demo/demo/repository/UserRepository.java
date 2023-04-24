package com.demo.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.demo.demo.modal.User;

public interface UserRepository extends MongoRepository<User,String>{

	Optional<User> findByEmailAndPassword(String email, String password);
	
}
